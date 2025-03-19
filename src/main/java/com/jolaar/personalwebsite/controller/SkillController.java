package com.jolaar.personalwebsite.controller;


import com.jolaar.personalwebsite.dto.SkillDTO;
import com.jolaar.personalwebsite.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService){
        this.skillService = skillService;
    }

    @GetMapping("skill")
    public ResponseEntity<List<SkillDTO>> getSkills(){
        return ResponseEntity.ok(skillService.getSkills());
    }
}
