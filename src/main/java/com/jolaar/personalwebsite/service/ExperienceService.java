package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.ExperienceDTO;
import com.jolaar.personalwebsite.mapper.ExperienceMapper;
import com.jolaar.personalwebsite.model.Experience;
import com.jolaar.personalwebsite.repository.ExperienceRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceService {
    private final ExperienceRepository experienceRepository;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public List<ExperienceDTO> getAllExperiences() {
        List<Experience> experienceList = experienceRepository.findAll();

        if (experienceList.isEmpty()) {
            throw new ResourceNotFoundException("No experiences found");
        }

        return experienceList.stream()
                .map(ExperienceMapper.INSTANCE::toDTO) // Convert to DTOs
                .sorted(Comparator.comparing(ExperienceDTO::endDate).reversed())
                .collect(Collectors.toUnmodifiableList()); // Collect as a list of DTOs
    }

    public ExperienceDTO updateExperience(Long id, ExperienceDTO experienceDTO) {
        Experience existingExperience = experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found"));

        // Update fields
        existingExperience.setCompanyName(experienceDTO.companyName());
        existingExperience.setPosition(experienceDTO.position());
        existingExperience.setStartDate(experienceDTO.startDate());
        existingExperience.setEndDate(experienceDTO.endDate());
        existingExperience.setDescription(experienceDTO.description());

        Experience updatedExperience = experienceRepository.save(existingExperience);
        return ExperienceMapper.INSTANCE.toDTO(updatedExperience);
    }

    public ExperienceDTO addExperience(ExperienceDTO experienceDTO) {
        Experience experience = ExperienceMapper.INSTANCE.toEntity(experienceDTO);

        // Make sure ExperienceDetails are associated with this Experience
        experience.getDetails().forEach(detail -> detail.setExperience(experience));

        Experience savedExperience = experienceRepository.save(experience);
        return ExperienceMapper.INSTANCE.toDTO(savedExperience);
    }

    public ExperienceDTO getExperienceById(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found"));

        return ExperienceMapper.INSTANCE.toDTO(experience);
    }

    public void deleteExperience(Long id) {
        if (!experienceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Experience not found");
        }
        experienceRepository.deleteById(id);
    }

}
