package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.PersonalInfoDTO;
import com.jolaar.personalwebsite.mapper.PersonalInfoMapper;
import com.jolaar.personalwebsite.model.PersonalInfo;
import com.jolaar.personalwebsite.repository.PersonalInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonalInfoServiceTest {
    @Mock
    PersonalInfoRepository personalInfoRepository;
    @InjectMocks
    PersonalInfoService personalInfoService;

    private PersonalInfoDTO personalInfo;

    @BeforeEach
    public void setUp(){

        personalInfo = new PersonalInfoDTO();
        personalInfo.setFirstName("John");
        personalInfo.setLastName("Doe");
        personalInfo.setEmail("john.doe@example.com");
        personalInfo.setPhone("+1234567890");
        personalInfo.setAddress("123 Main St, Anytown, USA");
        personalInfo.setBio("Software developer with a passion for coding and technology.");
        personalInfo.setLinkedinUrl("https://www.linkedin.com/in/johndoe");
        personalInfo.setGithubUrl("https://github.com/johndoe");
        personalInfo.setWebsiteUrl("https://www.johndoe.com");
    }

    @Test
    public void getPersonalInfoThrowsException(){
        when(personalInfoRepository.findAll()).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, ()-> personalInfoService.getPersonalInfo());
        verify(personalInfoRepository, times(1)).findAll();
    }

    @Test
    public void getPersonalInfoReturnsPersonalInfoDTO(){
        when(personalInfoRepository.findAll())
                .thenReturn(List.of(PersonalInfoMapper.INSTANCE.toEntity(personalInfo)));
        PersonalInfoDTO retrievedPersonalInfo = personalInfoService.getPersonalInfo();
        assertNotNull(retrievedPersonalInfo);
        assertEquals(personalInfo, retrievedPersonalInfo);
        verify(personalInfoRepository, times(1)).findAll();
    }

    @Test
    public void addPersonalInfoReturnsPersonalInfoDTO(){
        when(personalInfoRepository.save(any(PersonalInfo.class))).thenReturn(PersonalInfoMapper.INSTANCE.toEntity(personalInfo));
        PersonalInfoDTO savedPersonalInfo = personalInfoService.addPersonalInfo(personalInfo);
        assertNotNull(savedPersonalInfo);
        assertEquals(personalInfo, savedPersonalInfo);
        verify(personalInfoRepository, times(1)).save(any(PersonalInfo.class));
    }
}
