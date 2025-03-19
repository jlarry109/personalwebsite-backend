package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.EducationDTO;
import com.jolaar.personalwebsite.mapper.EducationMapper;
import com.jolaar.personalwebsite.model.Education;
import com.jolaar.personalwebsite.repository.EducationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EducationServiceTest {

    @Mock
    private EducationRepository educationRepository;

    @InjectMocks
    private EducationService educationService;

    private EducationDTO education1;
    private EducationDTO education2;

    @BeforeEach
    public void setUp() {
        education1 = new EducationDTO();
        education1.setSchoolName("University of Example");
        education1.setDegree("Bachelor of Science in Computer Science");
        education1.setStartYear(2015);
        education1.setEndYear(2019);

        education2 = new EducationDTO();
        education2.setSchoolName("Duke University");
        education2.setDegree("Master of Science in Electrical and Computer Engineering");
        education2.setStartYear(2022);
        education2.setEndYear(2024);
    }

    @Test
    public void getEducationHistoryReturnsEducationList(){
        List<EducationDTO> expectedEducationHistory = List.of(education1, education2);

        when(educationRepository.findAll()).thenReturn(expectedEducationHistory.stream()
                .map(EducationMapper.INSTANCE::toEntity)
                .collect(Collectors.toUnmodifiableList()));


        // Sort expected education history by degree award date
        expectedEducationHistory = expectedEducationHistory
                .stream()
                .sorted(Comparator.comparing(EducationDTO::getEndYear).reversed())
                .collect(Collectors.toList());

        List<EducationDTO> savedEducationHistory = educationService.getEducationHistory();
        assertFalse(savedEducationHistory.isEmpty());
        assertEquals(expectedEducationHistory.getFirst(), savedEducationHistory.getFirst());
        assertEquals(expectedEducationHistory.getLast(), savedEducationHistory.getLast());
    }

    @Test
    public void getEducationHistoryThrowsException(){
        when(educationRepository.findAll()).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> educationService.getEducationHistory());
    }

    @Test
    public void addEducationReturnsEducationDTO(){
        when(educationRepository.save(any(Education.class))).thenReturn(EducationMapper.INSTANCE.toEntity(education1));
        EducationDTO savedEducation = educationService.addEducation(education1);
        assertNotNull(savedEducation);
        assertEquals(education1, savedEducation);
    }
}
