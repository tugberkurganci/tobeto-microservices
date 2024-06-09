package com.tobeto.project_service.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tobeto.project_service.entities.Project;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProjectRepository extends MongoRepository<Project, String> {
    Page<Project> findByProjectNameContainingIgnoreCaseOrderByStartDate(String projectName, Pageable pageable);
}

