package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.CertificationDTO;
import com.jolaar.personalwebsite.mapper.CertificationMapper;
import com.jolaar.personalwebsite.model.Certification;
import com.jolaar.personalwebsite.repository.CertificationRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificationService {
    private final CertificationRepository certificationRepository;

    public CertificationService(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }


    public List<CertificationDTO> getAllCertifications() {
        List<Certification> certifications = certificationRepository.findAll();
        if(certifications.isEmpty()){
            throw new ResourceNotFoundException("No certifications found");
        }

        return certifications.stream()
                .map(CertificationMapper.INSTANCE::toDTO)
                .sorted(Comparator.comparing(CertificationDTO::getDateEarned).reversed())
                .collect(Collectors.toUnmodifiableList());
    }

    public CertificationDTO addCertification(CertificationDTO certificationDTO) {
        Certification certification = CertificationMapper.INSTANCE.toEntity(certificationDTO);

        Certification savedCertification = certificationRepository.save(certification);

        return CertificationMapper.INSTANCE.toDTO(savedCertification);
    }

}
