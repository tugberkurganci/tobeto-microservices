package com.tobeto.project_service.services.concretes;


import com.tobeto.project_service.core.exception.ProjectNotFoundException;
import com.tobeto.project_service.entities.Project;
import com.tobeto.project_service.repositories.ProjectRepository;
import com.tobeto.project_service.services.abstracts.ProjectService;
import com.tobeto.project_service.services.dtos.request.ProjectRequest;
import com.tobeto.project_service.services.dtos.response.ProjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectManager implements ProjectService {

    private ProjectRepository projectRepository;


    public ProjectManager(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(ProjectResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponse getProjectById(String id) {
        Project project = projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Project not found with id: " + id));

        return new ProjectResponse(project);
    }


    @Override
    public Project getProjectEntityById(String id) {
        Project project = projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Project not found with id: " + id));

        return project;
    }

    @Override
    public ProjectResponse addProject(ProjectRequest projectRequest) {
        Project project = projectRequest.toEntity();
        project = projectRepository.save(project);
        return new ProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(String id, ProjectRequest projectRequest) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + id));

        if (projectRequest.projectName() != null) {
            project.setProjectName(projectRequest.projectName());
        }

        if (projectRequest.startDate() != null) {
            project.setStartDate(projectRequest.startDate());
        }

        if (projectRequest.endDate() != null) {
            project.setEndDate(projectRequest.endDate());
        }

        project = projectRepository.save(project);
        return new ProjectResponse(project);
    }


    @Override
    public void deleteProject(String id) {
        Project project = projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Project not found with id: " + id));

        projectRepository.delete(project);
    }
    public Page<ProjectResponse> getAllProjects(int page, int size, String similarProjectName, String sortOrder) {
        Sort.Direction direction = sortOrder.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, direction, "startDate");

        return projectRepository.findByProjectNameContainingIgnoreCaseOrderByStartDate(similarProjectName,pageable).map(ProjectResponse::new);
    }

    @Override
    public boolean checkProjectExist(String id) {
        return projectRepository.existsById(id);
    }

}
