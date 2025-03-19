package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.ProjectDTO;
import com.jolaar.personalwebsite.mapper.ProjectMapper;
import com.jolaar.personalwebsite.model.Project;
import com.jolaar.personalwebsite.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    ProjectRepository projectRepository;
    @InjectMocks
    ProjectService projectService;

    private ProjectDTO project1;
    private ProjectDTO project2;

    @BeforeEach
    public void setUp(){
        project1 = new ProjectDTO();
        project1.setTitle("Personal Portfolio");
        project1.setDescription("A personal portfolio website to showcase projects and skills.");
        project1.setGithubLink("https://github.com/johndoe/portfolio");
        project1.setLiveDemoLink("https://www.johndoeportfolio.com");
        project1.setDateCreated(LocalDate.of(2023, 5, 1));

        project2 = new ProjectDTO();
        project2.setTitle("Work projects");
        project2.setDescription("A list of all project solely completed or contributed to at Amazon Artificial General Intelligence");
        project2.setGithubLink("https://github.com/jox-laar/work-projects");
        project2.setLiveDemoLink("https://www,joxlaarworkprojs.com");
        project2.setDateCreated(LocalDate.of(2024, 1, 21));
    }

    @Test
    public void ProjectServiceGetProjectsThrowsException(){
        when(projectRepository.findAll()).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, ()-> projectService.getProjects());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    public void ProjectServiceGetProjectsReturnsProjects(){
        when(projectRepository.findAll()).thenReturn(
                List.of(ProjectMapper.INSTANCE.toEntity(project1), ProjectMapper.INSTANCE.toEntity(project2)));
        List<ProjectDTO> returnedProjects = projectService.getProjects();
        List<ProjectDTO> expectedProjects = Stream.of(project1, project2)
                .sorted(Comparator.comparing(ProjectDTO::getDateCreated).reversed())
                .toList();
        assertFalse(returnedProjects.isEmpty());
        assertEquals(expectedProjects.getFirst(), returnedProjects.getFirst());
        assertEquals(expectedProjects.getLast(), returnedProjects.getLast());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    public void ProjectServiceGetProjectByIdThrowsResourceNotFoundException(){
        when(projectRepository.findById(anyLong())).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, ()-> projectService.getProjectById(anyLong()));
        verify(projectRepository, times(1)).findById(anyLong());
    }

    @Test
    public void ProjectServiceAddProjectReturnsProjectDTO(){

        when(projectRepository.save(any(Project.class))).thenReturn(ProjectMapper.INSTANCE.toEntity(project2));

        ProjectDTO savedProject = projectService.addProject(project2);
        assertNotNull(savedProject);
        assertEquals(project2, savedProject);
        verify(projectRepository, times(1)).save(any(Project.class));
    }
}
