package com.tobeto_case.task_service.services.abstracts;

import com.tobeto_case.task_service.services.dtos.request.TaskRequest;
import com.tobeto_case.task_service.services.dtos.response.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> getAllTasks();
    TaskResponse getTaskById(String id);
    TaskResponse addTask(TaskRequest taskRequest);
    TaskResponse updateTask(String id, TaskRequest taskRequest);
    void deleteTask(String id);
    List<TaskResponse> getTasksByProjectId(String id);

    List<TaskResponse> sortTasksByStatus(String status,String projectId);
}
