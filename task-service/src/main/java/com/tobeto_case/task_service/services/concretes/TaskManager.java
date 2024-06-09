package com.tobeto_case.task_service.services.concretes;


import com.tobeto_case.task_service.core.exception.ProjectNotFoundException;
import com.tobeto_case.task_service.core.exception.TaskNotFoundException;
import com.tobeto_case.task_service.entities.Task;
import com.tobeto_case.task_service.entities.TaskStatus;
import com.tobeto_case.task_service.event.ProcessTaskEvent;
import com.tobeto_case.task_service.repositories.TaskRepository;
import com.tobeto_case.task_service.services.abstracts.TaskService;
import com.tobeto_case.task_service.services.dtos.request.TaskRequest;
import com.tobeto_case.task_service.services.dtos.response.TaskResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskManager implements TaskService {

    private TaskRepository taskRepository;
    private WebClient webClient;
    private KafkaTemplate<String,ProcessTaskEvent> kafkaTemplate;

    public TaskManager(TaskRepository taskRepository, WebClient webClient,KafkaTemplate kafkaTemplate) {
        this.webClient = webClient;
        this.taskRepository = taskRepository;
        this.kafkaTemplate=kafkaTemplate;
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse getTaskById(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        return new TaskResponse(task);
    }

    @Override
    public List<TaskResponse> getTasksByProjectId(String id) {
        List<Task> tasks = taskRepository.findByProjectId(id);

        return tasks.stream().map(TaskResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> sortTasksByStatus(String status, String projectId) {
        TaskStatus taskStatus = TaskStatus.valueOf(status.toUpperCase());
        return taskRepository.sortTasksByStatus(taskStatus, projectId);
    }

    @Override
    public TaskResponse addTask(TaskRequest taskRequest) {
        final String projectId = taskRequest.projectId();
        Task task = taskRequest.toEntity();
        String uri = "http://project-service/api/v1/projects/exist/" + projectId;

        Boolean result = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Boolean.class).block();

        if (result) {
            task.setProjectId(projectId);
            task.setStatus(TaskStatus.NEW);
            task = taskRepository.save(task);



        kafkaTemplate.send("processTopic",new ProcessTaskEvent(task.getId(),randomProcessorType()));
        } else {

            throw new ProjectNotFoundException("Project not found");
        }

        return new TaskResponse(task);
    }

    private String randomProcessorType() {

        Random random = new Random();
        if(random.nextInt()%2==0)return "imageProcessing";else return "dataProcessing";
    }


    private void checkTaskDateBeforeProjectEndTime(Date taskDate, Date projectEndTime) {
        if (taskDate.after(projectEndTime)) {
            throw new RuntimeException("Error: Task date is after project end time.");
        }

    }

    @Override
    public TaskResponse updateTask(String id, TaskRequest taskRequest) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));


        if (taskRequest.title() != null) {
            task.setTitle(taskRequest.title());
        }


        if (taskRequest.description() != null) {
            task.setDescription(taskRequest.description());
        }


        if (taskRequest.createdDate() != null) {
            task.setCreatedDate(taskRequest.createdDate());
        }


        if (taskRequest.status() != null) {
            task.setStatus(taskRequest.status());
        }

        task = taskRepository.save(task);
        return new TaskResponse(task);
    }


    @Override
    public void deleteTask(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        taskRepository.delete(task);
    }
    @KafkaListener(topics = "updateTask")
    public void consumeMessage(ProcessTaskEvent event) {
        if (event.getProcessorType().equals("access")) {

            Optional<Task> inDb = taskRepository.findById(event.getTaskId());
            if (inDb.isPresent()) {

                Task task = inDb.get();
                task.setStatus(TaskStatus.COMPLETED);
                taskRepository.save(task);

            }
        }

    }}

