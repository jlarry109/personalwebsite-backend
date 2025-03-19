package com.jolaar.personalwebsite.repository;

import com.jolaar.personalwebsite.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    private Project project1;
    private Project project2;

    @BeforeEach
    public void setUp(){
        project1 = new Project();
        project1.setTitle("Personal Portfolio");
        project1.setDescription("A personal website to showcase my skills and projects. Built with Spring Boot and React.");
        project1.setGithubLink("https://github.com/username/personal-portfolio");
        project1.setLiveDemoLink("https://www.myportfolio.com");
        project1.setDateCreated(LocalDate.of(2023, 6, 15));

        project2 = new Project();
        project2.setTitle("E-commerce Platform");
        project2.setDescription("An online store platform with user authentication, product management, and payment gateway integration.");
        project2.setGithubLink("https://github.com/username/ecommerce-platform");
        project2.setLiveDemoLink("https://www.ecommerceplatform.com");
        project2.setDateCreated(LocalDate.of(2022, 11, 5));
    }

    @Test
    public void ProjectRepositorySaveReturnsSavedProject(){
        Project savedProject = projectRepository.save(project1);
        assertNotNull(savedProject);
        assertEquals(project1, savedProject);
    }

    @Test
    public void ProjectRepositoryFindAllReturnsAllSavedProjects(){
        projectRepository.save(project1);
        projectRepository.save(project2);
        List<Project> savedProjects = projectRepository.findAll();
        assertFalse(savedProjects.isEmpty());
        assertEquals(2, savedProjects.size());
        assertEquals(project1, savedProjects.getFirst());
        assertEquals(project2, savedProjects.getLast());
    }

    @Test
    public void ProjectRepositoryFindByIdReturnsSavedProject(){
        projectRepository.save(project1);
        Optional<Project> savedProject = projectRepository.findById(project1.getId());
        assertTrue(savedProject.isPresent());
        assertEquals(project1, savedProject.get());

        Optional<Project> nonExistentProject = projectRepository.findById(2 * project1.getId());
        assertFalse(nonExistentProject.isPresent());
    }
}
