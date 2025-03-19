package com.jolaar.personalwebsite.controller;

import com.jolaar.personalwebsite.dto.ExperienceDTO;
import com.jolaar.personalwebsite.service.ExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService){
        this.experienceService = experienceService;
    }

    @GetMapping("experience")
    public ResponseEntity<List<ExperienceDTO>> getAllExperiences(){
        return ResponseEntity.ok(experienceService.getAllExperiences());
    }

    @GetMapping("experience/{id}")
    public ResponseEntity<ExperienceDTO> getExperienceById(@PathVariable Long id){
        return ResponseEntity.ok(experienceService.getExperienceById(id));
    }

}
