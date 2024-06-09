package com.tobeto_case.task_service.services.dtos.response;


import com.tobeto_case.task_service.entities.Task;
import com.tobeto_case.task_service.entities.TaskStatus;

import java.util.Date;

public record TaskResponse(String id, String title, String description, Date createdDate, TaskStatus status) {
    public TaskResponse(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.getCreatedDate(), task.getStatus());
    }
}