package com.jolaar.personalwebsite.controller;

import com.jolaar.personalwebsite.dto.PersonalInfoDTO;
import com.jolaar.personalwebsite.service.PersonalInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class PersonalInfoController {

    private final PersonalInfoService personalInfoService;
    //     * GET /api/personal-info → Fetch basic personal details
    public PersonalInfoController(PersonalInfoService personalInfoService){
        this.personalInfoService = personalInfoService;
    }
    @GetMapping("personal-info")
    public ResponseEntity<PersonalInfoDTO> getPersonalInfo(){
        return ResponseEntity.ok(personalInfoService.getPersonalInfo());
    }
}
