package com.tobeto_case.task_service.repositories;

import com.tobeto_case.task_service.services.dtos.response.TaskResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.tobeto_case.task_service.entities.Task;
import com.tobeto_case.task_service.entities.TaskStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByProjectId(String projectId);

    @Query("{ 'status' : ?0, 'projectId' : ?1 }")
    List<TaskResponse> sortTasksByStatus(@Param("status") TaskStatus status, @Param("projectId") String projectId);
}

