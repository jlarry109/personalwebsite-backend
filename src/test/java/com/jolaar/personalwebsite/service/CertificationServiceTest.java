package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.CertificationDTO;
import com.jolaar.personalwebsite.mapper.CertificationMapper;
import com.jolaar.personalwebsite.model.Certification;
import com.jolaar.personalwebsite.repository.CertificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CertificationServiceTest {

    @Mock
    CertificationRepository certificationRepository;

    @InjectMocks
    CertificationService certificationService;

    private CertificationDTO certification1;
    private CertificationDTO certification2;
    private CertificationDTO certification3;

    @BeforeEach
    public void setUp(){
        certification1 = new CertificationDTO();
        certification1.setCertName("Java Programming Certification");
        certification1.setIssuingOrg("Oracle");
        certification1.setDateEarned(LocalDate.of(2023, 5, 15));
        certification1.setCertUrl("https://www.oracle.com/certifications/java-programming");

        certification2 = new CertificationDTO();
        certification2.setCertName("AWS Certified Solutions Architect");
        certification2.setIssuingOrg("Amazon Web Services");
        certification2.setDateEarned(LocalDate.of(2024, 2, 10));
        certification2.setCertUrl("https://aws.amazon.com/certification/certified-solutions-architect-associate/");

        certification3 = new CertificationDTO();
        certification3.setCertName("Microsoft Certified: Azure Fundamentals");
        certification3.setIssuingOrg("Microsoft");
        certification3.setDateEarned(LocalDate.of(2023, 11, 25));
        certification3.setCertUrl("https://learn.microsoft.com/en-us/certifications/azure-fundamentals/");
    }

    @Test
    public void getAllCertificationReturnsAllCertifications() {
        List<CertificationDTO> expected = List.of(certification1, certification2, certification3);

        doReturn(expected.stream()
                .map(CertificationMapper.INSTANCE::toEntity)
                .collect(Collectors.toUnmodifiableList())
        ).when(certificationRepository).findAll();

        // Sort by date earned
        expected = expected.stream()
                .sorted(Comparator.comparing(CertificationDTO::getDateEarned).reversed())
                .collect(Collectors.toUnmodifiableList());
        List<CertificationDTO> result = certificationService.getAllCertifications();
        assertFalse(result.isEmpty());
        assertEquals(expected.size(), result.size());
        assertEquals(expected.getFirst(), result.getFirst());
        assertEquals(expected.get(1), result.get(1));
        assertEquals(expected.getLast(), result.getLast());
    }

    @Test
    public void getAllCertificationThrowsException(){
        doThrow(ResourceNotFoundException.class).when(certificationRepository).findAll();

        assertThrows(ResourceNotFoundException.class, ()-> certificationService.getAllCertifications());
        verify(certificationRepository, times(1)).findAll();
    }

    @Test
    public void addCertificationReturnsCertification(){
        doReturn(CertificationMapper.INSTANCE.toEntity(certification2)).when(certificationRepository).save(any(Certification.class));

        CertificationDTO savedCertification = certificationService.addCertification(certification2);
        assertNotNull(savedCertification);
        assertEquals(certification2, savedCertification);
    }
}
