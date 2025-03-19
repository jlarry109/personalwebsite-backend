package com.jolaar.personalwebsite.controller;

import com.jolaar.personalwebsite.dto.EducationDTO;
import com.jolaar.personalwebsite.service.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService){
        this.educationService = educationService;
    }
    @GetMapping("education")
    public ResponseEntity<List<EducationDTO>> getEducationHistory(){
        return ResponseEntity.ok(educationService.getEducationHistory());
    }
}
