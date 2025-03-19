package com.jolaar.personalwebsite.mapper;

import com.jolaar.personalwebsite.dto.EducationDTO;
import com.jolaar.personalwebsite.model.Education;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EducationMapper {

    EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

    EducationDTO toDTO (Education education);
    Education toEntity(EducationDTO educationDTO);
}
