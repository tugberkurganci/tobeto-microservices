package com.tobeto.project_service.services.dtos.response;

import com.tobeto.project_service.entities.Project;

import java.util.Date;



public record ProjectResponse(String id, String projectName, Date startDate, Date endDate) {
    public ProjectResponse(Project project) {
        this(project.getId(), project.getProjectName(), project.getStartDate(), project.getEndDate());
    }
}
