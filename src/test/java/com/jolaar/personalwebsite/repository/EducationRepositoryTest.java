package com.jolaar.personalwebsite.repository;

import com.jolaar.personalwebsite.model.Education;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EducationRepositoryTest {

    @Autowired
    EducationRepository educationRepository;
    private static Education education1;

    @BeforeEach
    public void setUp(){
        education1 = new Education();
        education1.setDegree("Bachelor of Science");
        education1.setSchoolName("Duke University");
        education1.setStartYear(2022);
        education1.setEndYear(2024);
    }

    @Test
    public void EducationRepositorySaveReturnsSavedEducation(){
        Education savedEducation = educationRepository.save(education1);
        assertNotNull(savedEducation);
        assertEquals(education1.getId(), savedEducation.getId());
        assertEquals(education1.getDegree(), savedEducation.getDegree());
        assertEquals(education1.getSchoolName(), savedEducation.getSchoolName());
        assertEquals(education1.getStartYear(), savedEducation.getStartYear());
        assertEquals(education1.getEndYear(), savedEducation.getEndYear());
    }
    @Test
    public void EducationRepositoryFindAllReturnsSavedEducationHistory(){
        // Arrange
        Education education2 = new Education();
        education2 = new Education();
        education2.setDegree("Master of Research");
        education2.setSchoolName("University of Manchester");
        education2.setStartYear(2020);
        education2.setEndYear(2020);

        // Act
        educationRepository.save(education1);
        educationRepository.save(education2);

        // Assert
        List<Education> savedEducation = educationRepository.findAll();
        assertEquals(2, savedEducation.size());
        assertEquals(education1, savedEducation.getFirst());
        assertEquals(education2, savedEducation.getLast());
    }
}
