package com.tobeto.project_service.services.abstracts;


import com.tobeto.project_service.entities.Project;
import com.tobeto.project_service.services.dtos.request.ProjectRequest;
import com.tobeto.project_service.services.dtos.response.ProjectResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    List<ProjectResponse> getAllProjects();

    ProjectResponse getProjectById(String id);

    ProjectResponse addProject(ProjectRequest projectRequest);

    ProjectResponse updateProject(String id, ProjectRequest projectRequest);

    void deleteProject(String id);

    Project getProjectEntityById(String id);

    Page<ProjectResponse> getAllProjects(int page, int size, String similarProjectName, String sortOrder);

    boolean checkProjectExist(String id);
}