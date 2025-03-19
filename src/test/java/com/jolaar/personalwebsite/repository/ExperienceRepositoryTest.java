package com.jolaar.personalwebsite.repository;

import com.jolaar.personalwebsite.model.Experience;
import com.jolaar.personalwebsite.model.ExperienceDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ExperienceRepositoryTest {

    @Autowired
    private ExperienceRepository experienceRepository;

    private Experience experience1;
    private Experience experience2;
    private ExperienceDetail detail1;
    private ExperienceDetail detail2;
    private ExperienceDetail detail3;
    private ExperienceDetail detail4;

    @BeforeEach
    public void setUp(){
        experience1 = new Experience();
        experience1.setCompanyName("ABC Corp");
        experience1.setPosition("Software Developer");
        experience1.setStartDate(LocalDate.of(2020, 1, 15));
        experience1.setEndDate(LocalDate.of(2022, 6, 30));
        experience1.setDescription("Worked on backend services for e-commerce platform.");
        detail1 = new ExperienceDetail();
        detail1.setTaskDescription("Developed REST APIs for product catalog.");
        detail1.setExperience(experience1);

        detail2 = new ExperienceDetail();
        detail2.setTaskDescription("Integrated payment gateway.");
        detail2.setExperience(experience1);

        experience1.setDetails(Arrays.asList(detail1, detail2));

        experience2 = new Experience();
        experience2.setCompanyName("XYZ Ltd");
        experience2.setPosition("Senior Software Engineer");
        experience2.setStartDate(LocalDate.of(2022, 7, 1));
        experience2.setEndDate(LocalDate.of(2024, 2, 28));
        experience2.setDescription("Led a team for developing AI-based product features.");

        detail3 = new ExperienceDetail();
        detail3.setTaskDescription("Led a team of 5 engineers for developing AI features.");
        detail3.setExperience(experience2);

        detail4 = new ExperienceDetail();
        detail4.setTaskDescription("Optimized the performance of machine learning algorithms.");
        detail4.setExperience(experience2);

        experience2.setDetails(Arrays.asList(detail3, detail4));
    }

    @Test
    public void ExperienceRepositorySaveReturnsSavedExperience(){
        Experience savedExperience = experienceRepository.save(experience1);
        assertNotNull(savedExperience);
        assertEquals(experience1, savedExperience);
    }

    @Test
    public void ExperienceRepositorySaveAllReturnsAllSavedExperience(){
        List<Experience> savedList = experienceRepository.saveAll(List.of(experience1, experience2));
        assertFalse(savedList.isEmpty());
        assertEquals(experience1, savedList.getFirst());
        assertEquals(experience2, savedList.getLast());
    }

    @Test
    public void ExperienceRepositoryFindByIdReturnsExperience(){
        experienceRepository.saveAll(List.of(experience1, experience2));
        Optional<Experience> optionalSavedExperience1 = experienceRepository.findById(experience1.getId());
        Optional<Experience> optionalSavedExperience2 = experienceRepository.findById(experience2.getId());
        assertTrue(optionalSavedExperience1.isPresent());
        assertTrue(optionalSavedExperience2.isPresent());

        Experience savedExperience1 = optionalSavedExperience1.get();
        Experience savedExperience2 = optionalSavedExperience2.get();
        assertEquals(experience1, savedExperience1);
        assertEquals(experience2, savedExperience2);
        assertEquals(experience1.getId(), savedExperience1.getId());
        assertEquals(experience2.getId(), savedExperience2.getId());
    }

    @Test
    public void ExperienceRepositoryDeleteById(){
        experienceRepository.save(experience1);
        experienceRepository.save(experience2);
        List<Experience> experienceList = experienceRepository.findAll();
        assertEquals(2, experienceList.size());

        experienceRepository.deleteById(experience1.getId());
        experienceList = experienceRepository.findAll();
        assertEquals(1, experienceList.size());

        experienceRepository.deleteById(experience2.getId());
        experienceList = experienceRepository.findAll();
        assertTrue(experienceList.isEmpty());
    }

    @Test
    public void ExperienceRepositoryExistsById(){
        boolean negative = experienceRepository.existsById(1L);
        assertFalse(negative);
        experienceRepository.save(experience1);
        boolean positive = experienceRepository.existsById(experience1.getId());
        assertTrue(positive);
    }
}
