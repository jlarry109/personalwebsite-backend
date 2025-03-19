package com.jolaar.personalwebsite.controller;

import com.jolaar.personalwebsite.dto.ProjectDTO;
import com.jolaar.personalwebsite.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping("project")
    public ResponseEntity<List<ProjectDTO>> getProjects(){
        return ResponseEntity.ok(projectService.getProjects());
    }

    @GetMapping("projects/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id){
        return ResponseEntity.ok(projectService.getProjectById(id));
    }
}
