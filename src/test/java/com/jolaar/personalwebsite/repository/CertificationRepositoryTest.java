package com.jolaar.personalwebsite.repository;

import com.jolaar.personalwebsite.model.Certification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CertificationRepositoryTest {

    @Autowired
    private CertificationRepository certificationRepository;
    private static Certification certification;

    @BeforeEach
    public void setUp(){
        LocalDate localDate = LocalDate.of(2025, 2, 28);
        certification = new Certification(
                "AWS Cloud Developer",
                "AWS",
                localDate,
                "mycertificate.org"
        );

    }
    @Test
    public void CertificateRepositorySaveReturnsSavedCertificate(){
        // Arrange

        // Act
        Certification savedCertification = certificationRepository.save(certification);

        // Assert
        assertNotNull(savedCertification);
        assertEquals(certification.getCertName(), savedCertification.getCertName());
        assertEquals(certification.getId(), savedCertification.getId());
        assertEquals(certification.getCertUrl(), savedCertification.getCertUrl());
        assertEquals(certification.getDateEarned(), savedCertification.getDateEarned());
        assertEquals(certification.getIssuingOrg(), savedCertification.getIssuingOrg());
    }

    @Test
    public void CertificateRepositorySaveAllReturnsSavedCertificates() {
        Certification certification1 = new Certification(
                "Google Cloud Professional Cloud Architect",
                "Google Cloud",
                LocalDate.of(2024, 1, 15),
                "https://cloud.google.com/certification/cloud-architect"
        );

        Certification certification2 = new Certification(
                "Microsoft Certified: Azure Solutions Architect Expert",
                "Microsoft",
                LocalDate.of(2023, 10, 30),
                "https://learn.microsoft.com/en-us/certifications/azure-solutions-architect"
        );
        List<Certification> certificationList = List.of(certification, certification1, certification2);
        List<Certification> results = certificationRepository.saveAll(certificationList);
        assertEquals(certificationList.size(), results.size());
        assertSame(certification, certificationList.getFirst());
        assertSame(certification1, certificationList.get(1));
        assertSame(certification2, certificationList.getLast());
    }

    @Test
    public void CertificateRepositoryFindAllReturnsSavedCertificates(){
        Certification certification1 = new Certification(
                "Google Cloud Professional Cloud Architect",
                "Google Cloud",
                LocalDate.of(2024, 1, 15),
                "https://cloud.google.com/certification/cloud-architect"
        );

        Certification certification2 = new Certification(
                "Microsoft Certified: Azure Solutions Architect Expert",
                "Microsoft",
                LocalDate.of(2023, 10, 30),
                "https://learn.microsoft.com/en-us/certifications/azure-solutions-architect"
        );
        List<Certification> certificationList = List.of(certification, certification1, certification2);
        for (Certification cert : certificationList) {
            certificationRepository.save(cert);
        }
        List<Certification> results = certificationRepository.findAll();
        assertEquals(certificationList.size(), results.size());
        assertSame(certification, certificationList.getFirst());
        assertSame(certification1, certificationList.get(1));
        assertSame(certification2, certificationList.getLast());
    }
}
