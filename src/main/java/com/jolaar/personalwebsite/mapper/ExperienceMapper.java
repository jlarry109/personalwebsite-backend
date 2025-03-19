package com.jolaar.personalwebsite.mapper;

import com.jolaar.personalwebsite.dto.ExperienceDTO;
import com.jolaar.personalwebsite.dto.ExperienceDetailDTO;
import com.jolaar.personalwebsite.model.Experience;
import com.jolaar.personalwebsite.model.ExperienceDetail;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExperienceMapper {
    ExperienceMapper INSTANCE = Mappers.getMapper(ExperienceMapper.class);

    @Mapping(target = "details", source = "details")
    ExperienceDTO toDTO(Experience experience);
    List<ExperienceDetailDTO> mapDetails(List<ExperienceDetail> details);

    Experience toEntity(ExperienceDTO experienceDTO);

    @Mapping(target = "taskDescription", source = "taskDescription") // Ensure this field exists in DTO
    ExperienceDetailDTO toDTO(ExperienceDetail experienceDetail);
    // Convert ExperienceDetailDTO -> ExperienceDetail
    List<ExperienceDetail> mapDetailDTOs(List<ExperienceDetailDTO> details);
}


