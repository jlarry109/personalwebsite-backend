package com.jolaar.personalwebsite.mapper;

import com.jolaar.personalwebsite.dto.CertificationDTO;
import com.jolaar.personalwebsite.model.Certification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CertificationMapper {

    CertificationMapper INSTANCE = Mappers.getMapper(CertificationMapper.class);

    CertificationDTO toDTO (Certification certification);

    Certification toEntity(CertificationDTO certificationDTO);
}
