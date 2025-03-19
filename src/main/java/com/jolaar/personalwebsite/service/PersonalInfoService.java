package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.PersonalInfoDTO;
import com.jolaar.personalwebsite.mapper.PersonalInfoMapper;
import com.jolaar.personalwebsite.model.PersonalInfo;
import com.jolaar.personalwebsite.repository.PersonalInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonalInfoService {

    PersonalInfoRepository personalInfoRepository;

    public PersonalInfoService(PersonalInfoRepository personalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
    }

    public PersonalInfoDTO getPersonalInfo() {
        return personalInfoRepository.findAll().stream()
                .findFirst()
                .map(PersonalInfoMapper.INSTANCE::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Personal info not found"));
    }

    public PersonalInfoDTO addPersonalInfo(PersonalInfoDTO personalInfoDTO) {
        PersonalInfo personalInfo = PersonalInfoMapper.INSTANCE.toEntity(personalInfoDTO);

        PersonalInfo savedPersonalInfo = personalInfoRepository.save(personalInfo);

        return PersonalInfoMapper.INSTANCE.toDTO(savedPersonalInfo);
    }

}
