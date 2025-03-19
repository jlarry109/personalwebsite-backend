package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.ProjectDTO;
import com.jolaar.personalwebsite.mapper.ProjectMapper;
import com.jolaar.personalwebsite.model.Project;
import com.jolaar.personalwebsite.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }
    public List<ProjectDTO> getProjects() {
        List<Project> projects = projectRepository.findAll();
        if(projects.isEmpty()){
            throw new ResourceNotFoundException("No projects to show");
        }
        return projects.stream()
                .map(ProjectMapper.INSTANCE::toDTO)
                .sorted(Comparator.comparing(ProjectDTO::getDateCreated).reversed())
                .collect(Collectors.toUnmodifiableList());
    }

    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        return ProjectMapper.INSTANCE.toDTO(project);
    }

    public ProjectDTO addProject(ProjectDTO projectDTO) {
        Project project = ProjectMapper.INSTANCE.toEntity(projectDTO);

        Project savedProject = projectRepository.save(project);

        return ProjectMapper.INSTANCE.toDTO(savedProject);
    }
}
