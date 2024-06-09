package com.tobeto_case.task_service.services.dtos.request;


import com.tobeto_case.task_service.entities.Task;
import com.tobeto_case.task_service.entities.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record TaskRequest(String projectId,@NotBlank String title,  String description, @NotNull Date createdDate, TaskStatus status) {
    public Task toEntity() {
        return new Task(null,null, this.title(), this.description(), this.createdDate(), this.status());
    }
}

