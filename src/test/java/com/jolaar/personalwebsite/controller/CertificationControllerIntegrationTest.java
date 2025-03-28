package com.jolaar.personalwebsite.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jolaar.personalwebsite.common.config.JacksonConfig;

import com.jolaar.personalwebsite.dto.CertificationDTO;
import com.jolaar.personalwebsite.service.CertificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CertificationControllerIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private CertificationService certificationService;

    @InjectMocks
    private CertificationController certificationController;

    ObjectMapper objectMapper;

    private CertificationDTO certification1;
    private CertificationDTO certification2;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(certificationController).build();
        objectMapper = new JacksonConfig().objectMapper();

        certification1 = new CertificationDTO();
        certification1.setCertName("Java Programming Certification");
        certification1.setIssuingOrg("Oracle");
        certification1.setDateEarned(LocalDate.of(2023, 5, 15));
        certification1.setCertUrl("https://www.oracle.com/certifications/java-programming");

        certification2 = new CertificationDTO();
        certification2.setCertName("AWS Developer Associate");
        certification2.setIssuingOrg("Amazon Web Services.Inc");
        certification2.setDateEarned(LocalDate.of(2025, 7, 17));
        certification2.setCertUrl("https://www.aws.com/certifications/developer-associate");
    }

    @Test
    public void testGetAllCertificationsSuccess() throws Exception {
        when(certificationService.getAllCertifications()).thenReturn(List.of(certification2, certification1));

        mockMvc.perform(get("/api/certification"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Verify content type
                .andExpect(jsonPath("$[0].id").value(certification2.getId())) // Verify first element in list
                .andExpect(jsonPath("$[0].certName").value(certification2.getCertName())) // Verify name of first (most recent) certification
                .andExpect(jsonPath("$[0].dateEarned").value(certification2.getDateEarned().toString())) // Verify dateEarned of first (most recent) certification
                .andExpect(jsonPath("$[0].issuingOrg").value(certification2.getIssuingOrg()))
                .andExpect(jsonPath("$[0].certUrl").value(certification2.getCertUrl()))
                .andExpect(jsonPath("$[1].id").value(certification1.getId())) // Verify second element in list
                .andExpect(jsonPath("$[1].certName").value(certification1.getCertName())) // Verify name of second certification
                .andExpect(jsonPath("$[1].dateEarned").value(certification1.getDateEarned().toString())) // Verify dateEarned of second certification
                .andExpect(jsonPath("$[1].issuingOrg").value(certification1.getIssuingOrg()))
                .andExpect(jsonPath("$[1].certUrl").value(certification1.getCertUrl()));
        verify(certificationService, times(1)).getAllCertifications();
    }
}
