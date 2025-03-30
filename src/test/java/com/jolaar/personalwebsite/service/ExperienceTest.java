package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.ExperienceDTO;
import com.jolaar.personalwebsite.mapper.ExperienceMapper;
import com.jolaar.personalwebsite.model.Experience;
import com.jolaar.personalwebsite.model.ExperienceDetail;
import com.jolaar.personalwebsite.repository.ExperienceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExperienceTest {

    @Mock
    private ExperienceRepository experienceRepository;

    @InjectMocks
    private ExperienceService experienceService;

    private Experience experience1;
    private Experience experience2;

    @BeforeEach
    public void setUp(){
        experience1 = new Experience();
        experience1.setCompanyName("ABC Corp");
        experience1.setPosition("Software Developer");
        experience1.setStartDate(LocalDate.of(2020, 1, 15));
        experience1.setEndDate(LocalDate.of(2022, 6, 30));
        experience1.setDescription("Worked on backend services for e-commerce platform.");
        ExperienceDetail detail1 = new ExperienceDetail();
        detail1.setTaskDescription("Developed REST APIs for product catalog.");
        detail1.setExperience(experience1);

        ExperienceDetail detail2 = new ExperienceDetail();
        detail2.setTaskDescription("Integrated payment gateway.");
        detail2.setExperience(experience1);

        experience1.setDetails(Arrays.asList(detail1, detail2));

        experience2 = new Experience();
        experience2.setCompanyName("XYZ Ltd");
        experience2.setPosition("Senior Software Engineer");
        experience2.setStartDate(LocalDate.of(2022, 7, 1));
        experience2.setEndDate(LocalDate.of(2024, 2, 28));
        experience2.setDescription("Led a team for developing AI-based product features.");

        ExperienceDetail detail3 = new ExperienceDetail();
        detail3.setTaskDescription("Led a team of 5 engineers for developing AI features.");
        detail3.setExperience(experience2);

        ExperienceDetail detail4 = new ExperienceDetail();
        detail4.setTaskDescription("Optimized the performance of machine learning algorithms.");
        detail4.setExperience(experience2);

        experience2.setDetails(Arrays.asList(detail3, detail4));
    }

    @Test
    public void getAllExperienceThrowsException(){
        when(experienceRepository.findAll()).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, ()-> experienceService.getAllExperiences());
        verify(experienceRepository, times(1)).findAll();
    }

    @Test
    public void getAllExperienceReturnsExperienceList(){
        List<Experience> expectedExperience = List.of(experience1, experience2);
        List<ExperienceDTO> expectedExperienceDTO = expectedExperience.stream()
                .map(ExperienceMapper.INSTANCE::toDTO) // Convert to DTOs
                .sorted(Comparator.comparing(ExperienceDTO::endDate).reversed())
                .toList();
        when(experienceRepository.findAll()).thenReturn(expectedExperience);

        List<ExperienceDTO> savedExperience = experienceService.getAllExperiences();
        assertFalse(expectedExperienceDTO.isEmpty());
        assertEquals(expectedExperienceDTO.size(), savedExperience.size());
        assertEquals(expectedExperienceDTO.getFirst(), savedExperience.getFirst());
        assertEquals(expectedExperienceDTO.getLast(), savedExperience.getLast());
        verify(experienceRepository, times(1)).findAll();
    }

    @Test
    public void updateExperienceThrowsException(){
        when(experienceRepository.findById(anyLong())).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, ()-> experienceService.updateExperience(1L, ExperienceMapper.INSTANCE.toDTO(experience1)));
        verify(experienceRepository, times(1)).findById(anyLong());
    }

    @Test
    public void updateExperienceReturnsUpdatedExperienceDTO(){
        experience2.setId(1L);
        when(experienceRepository.findById(anyLong())).thenReturn(Optional.ofNullable(experience2));
        when(experienceRepository.save(any(Experience.class))).thenReturn(experience1);

        ExperienceDTO updatedExperience = experienceService.updateExperience(experience2.getId(), ExperienceMapper.INSTANCE.toDTO(experience1));
        assertNotNull(updatedExperience);
        assertEquals(updatedExperience, ExperienceMapper.INSTANCE.toDTO(experience1));
        verify(experienceRepository, times(1)).findById(experience2.getId());
        verify(experienceRepository, times(1)).save(experience2);
    }

    @Test
    public void addExperienceReturnsAddedExperienceDTO(){
        when(experienceRepository.save(any(Experience.class))).thenReturn(experience2);
        ExperienceDTO savedExperience = experienceService.addExperience(ExperienceMapper.INSTANCE.toDTO(experience2));
        assertNotNull(savedExperience);
        assertEquals(ExperienceMapper.INSTANCE.toDTO(experience2), savedExperience);
        verify(experienceRepository, times(1)).save(experience2);
    }

    @Test
    public void getExperienceByIdThrowsException(){
        when(experienceRepository.findById(anyLong())).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, ()-> experienceService.getExperienceById(1L));
        verify(experienceRepository, times(1)).findById(1L);
    }

    @Test
    public void getExperienceByIdReturnsExperienceDTO(){
        when(experienceRepository.findById(anyLong())).thenReturn(Optional.of(experience2));

        ExperienceDTO savedExperience = experienceService.getExperienceById(1L);
        assertNotNull(savedExperience);
        assertEquals(ExperienceMapper.INSTANCE.toDTO(experience2), savedExperience);
        verify(experienceRepository, times(1)).findById(1L);
    }

    @Test
    public void deleteExperienceThrowsException(){
        when(experienceRepository.existsById(anyLong())).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, ()->experienceService.deleteExperience(1L));
        verify(experienceRepository, times(1)).existsById(1L);
    }

    @Test
    public void testDeleteExperience(){
        when(experienceRepository.existsById(anyLong())).thenReturn(true);

        doNothing().when(experienceRepository).deleteById(1L);

        experienceService.deleteExperience(1L);

        verify(experienceRepository, times(1)).existsById(1L);
        verify(experienceRepository, times(1)).deleteById(1L);
    }
}
