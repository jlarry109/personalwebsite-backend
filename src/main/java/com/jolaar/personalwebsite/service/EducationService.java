package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.EducationDTO;
import com.jolaar.personalwebsite.mapper.EducationMapper;
import com.jolaar.personalwebsite.model.Education;
import com.jolaar.personalwebsite.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<EducationDTO> getEducationHistory() {
        List<Education> educationHistory = educationRepository.findAll();

        if (educationHistory.isEmpty()) {
            throw new ResourceNotFoundException("No education record found");
        }
        return educationHistory.stream()
                .map(EducationMapper.INSTANCE::toDTO) // Convert to DTOs
                .sorted(Comparator.comparing(EducationDTO::getEndYear).reversed())
                .collect(Collectors.toList()); // Collect as a list of DTOs
    }

    public EducationDTO addEducation(EducationDTO educationDTO) {
        Education education = EducationMapper.INSTANCE.toEntity(educationDTO);

        Education savedEducation = educationRepository.save(education);

        return EducationMapper.INSTANCE.toDTO(savedEducation);
    }

}
