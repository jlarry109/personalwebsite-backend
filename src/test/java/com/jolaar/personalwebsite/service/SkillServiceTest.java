package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.enums.ProficiencyLevel;
import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.SkillDTO;
import com.jolaar.personalwebsite.mapper.SkillMapper;
import com.jolaar.personalwebsite.model.Skill;
import com.jolaar.personalwebsite.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SkillServiceTest {
    @Mock
    private SkillRepository skillRepository;
    @InjectMocks
    private SkillService skillService;

    private SkillDTO skill1;
    private SkillDTO skill2;

    @BeforeEach
    public void setUp(){
        skill1 = new SkillDTO();
        skill1.setSkillName("Java Programming");
        skill1.setProficiencyLevel(ProficiencyLevel.EXPERT);

        skill2 = new SkillDTO();
        skill2.setSkillName("C/C++");
        skill2.setProficiencyLevel(ProficiencyLevel.INTERMEDIATE);
    }

    @Test
    public void SkillServiceGetSkillsThrowsResourceNotFoundException(){
        when(skillRepository.findAll()).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, ()-> skillService.getSkills());
        verify(skillRepository, times(1)).findAll();
    }

    @Test
    public void SkillServiceGetSkillsReturnsSkillDTOs(){
        when(skillRepository.findAll()).thenReturn(
                List.of(SkillMapper.INSTANCE.toEntity(skill1), SkillMapper.INSTANCE.toEntity(skill2)));
        List<SkillDTO> returnedSkills = skillService.getSkills();
        List<SkillDTO> expectedSkills = Stream.of(skill1, skill2)
                .sorted(Comparator.comparing(SkillDTO::getSkillName))
                .toList();
        assertFalse(returnedSkills.isEmpty());
        assertEquals(expectedSkills.getFirst(), returnedSkills.getFirst());
        assertEquals(expectedSkills.getLast(), returnedSkills.getLast());
        verify(skillRepository, times(1)).findAll();
    }

    @Test
    public void SkillServiceAddSkillReturnsSkillDTO(){
        when(skillRepository.save(any(Skill.class))).thenReturn(SkillMapper.INSTANCE.toEntity(skill2));

        SkillDTO savedSkill = skillService.addSkill(skill2);
        assertNotNull(savedSkill);
        assertEquals(skill2, savedSkill);
        verify(skillRepository, times(1)).save(any(Skill.class));
    }
}
