package com.jolaar.personalwebsite.controller;

import com.jolaar.personalwebsite.dto.CertificationDTO;
import com.jolaar.personalwebsite.model.Certification;
import com.jolaar.personalwebsite.service.CertificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class CertificationController {
    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService){
        this.certificationService = certificationService;
    }

    @GetMapping("certification")
    public ResponseEntity<List<CertificationDTO>> getAllCertifications(){
        return ResponseEntity.ok(certificationService.getAllCertifications());
    }

}
