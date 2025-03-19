package com.jolaar.personalwebsite.mapper;

import com.jolaar.personalwebsite.dto.PersonalInfoDTO;
import com.jolaar.personalwebsite.model.PersonalInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonalInfoMapper {
    PersonalInfoMapper INSTANCE = Mappers.getMapper(PersonalInfoMapper.class);

    PersonalInfoDTO toDTO(PersonalInfo personalInfo);

    PersonalInfo toEntity(PersonalInfoDTO personalInfoDTO);
}

