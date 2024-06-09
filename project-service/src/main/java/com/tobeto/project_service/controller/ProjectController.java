package com.tobeto.project_service.controller;

import com.tobeto.project_service.services.abstracts.ProjectService;
import com.tobeto.project_service.services.dtos.request.ProjectRequest;
import com.tobeto.project_service.services.dtos.response.ProjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {


    private ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectResponse> getAllProjects() {
        return projectService.getAllProjects();
    }


    @GetMapping("/{id}")
    public ProjectResponse getProjectById(@PathVariable String id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/exist/{id}")
    public boolean checkProjectExist(@PathVariable String id) {
        return projectService.checkProjectExist(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse createProject(@Valid @RequestBody ProjectRequest projectRequest) {
        return projectService.addProject(projectRequest);
    }


    @PutMapping("/{id}")
    public ProjectResponse updateProject(@PathVariable String id, @Valid @RequestBody ProjectRequest projectRequest) {
        return projectService.updateProject(id, projectRequest);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
    }

    @GetMapping("/page")
    public Page<ProjectResponse> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String similarProjectName,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        return projectService.getAllProjects(page, size, similarProjectName, sortOrder);
    }
}