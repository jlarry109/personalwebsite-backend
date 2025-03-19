package com.jolaar.personalwebsite.mapper;

import com.jolaar.personalwebsite.dto.ProjectDTO;
import com.jolaar.personalwebsite.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDTO toDTO(Project project);

    Project toEntity(ProjectDTO projectDTO);
}
