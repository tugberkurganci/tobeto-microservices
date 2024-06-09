package com.tobeto_case.task_service.controller;


import com.tobeto_case.task_service.services.abstracts.TaskService;
import com.tobeto_case.task_service.services.dtos.request.TaskRequest;
import com.tobeto_case.task_service.services.dtos.response.TaskResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/sort/{status}/{projectId}")
    public List<TaskResponse> sortTasksByStatus(@PathVariable String status,@PathVariable String projectId) {
        return taskService.sortTasksByStatus(status,projectId);
    }
    @GetMapping("/project-tasks/{id}")
    public List<TaskResponse> getTasksByProjectId(@PathVariable String id) {
        return taskService.getTasksByProjectId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="project", fallbackMethod = "fallbackMethod")
    //@Retry(name="project")
    public TaskResponse createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return taskService.addTask(taskRequest);
    }

    public String fallbackMethod(TaskRequest taskRequest,RuntimeException runtimeException){
        return "Sth went wrong,please add task after some time";
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable String id, @Valid @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }
}
