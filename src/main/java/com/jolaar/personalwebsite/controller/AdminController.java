package com.jolaar.personalwebsite.controller;

import com.jolaar.personalwebsite.dto.*;
import com.jolaar.personalwebsite.model.Admin;
import com.jolaar.personalwebsite.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin") // Separate from /api/auth
public class AdminController {
    private final AdminService adminService;
    private final ExperienceService experienceService;
    private final EducationService educationService;
    private final SkillService skillService;
    private final CertificationService certificationService;
    private final ProjectService projectService;
    private final TestimonialService testimonialService;
    private final PersonalInfoService personalInfoService;

    public AdminController(AdminService adminService, ExperienceService experienceService, EducationService educationService,
                           SkillService skillService, CertificationService certificationService, ProjectService projectService,
                           TestimonialService testimonialService, PersonalInfoService personalInfoService) {
        this.adminService = adminService;
        this.experienceService = experienceService;
        this.educationService = educationService;
        this.skillService = skillService;
        this.certificationService = certificationService;
        this.projectService = projectService;
        this.testimonialService = testimonialService;
        this.personalInfoService = personalInfoService;
    }

    // Only logged-in admins can register a new admin
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')") // Restricts access
    public ResponseEntity<?> registerAdmin(@RequestBody AuthRequest admin) {
        Admin newAdmin = adminService.registerAdmin(admin);
        return ResponseEntity.ok("Admin registered: " + newAdmin.getUsername());
    }

    //  Only admins can add experience
    @PostMapping("experience")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addExperience(@RequestBody ExperienceDTO experienceDTO){
        experienceService.addExperience(experienceDTO);
        return ResponseEntity.ok("Experience added successfully");
    }

    // Only admins can update experience
    @PutMapping("experience/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateExperience(@PathVariable Long id, @RequestBody ExperienceDTO experienceDTO) {
        experienceService.updateExperience(id, experienceDTO);
        return ResponseEntity.ok("Experience updated successfully");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("experience/{id}")
    public ResponseEntity<String> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.ok("Experience deleted successfully");
    }

    @PostMapping("education")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addEducation(@RequestBody EducationDTO educationDTO){
        educationService.addEducation(educationDTO);
        return ResponseEntity.ok("Education added successfully");
    }

    @PostMapping("personal-info")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addPersonalInfo(@RequestBody PersonalInfoDTO personalInfoDTO){
        personalInfoService.addPersonalInfo(personalInfoDTO);
        return ResponseEntity.ok("Personal Info added successfully");
    }

    @PostMapping("skill")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addSkill(@RequestBody SkillDTO skillDTO){
        skillService.addSkill(skillDTO);
        return ResponseEntity.ok("Skill added successfully");
    }

    @PostMapping("certification")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addCertification(@RequestBody CertificationDTO certificationDTO){
        certificationService.addCertification(certificationDTO);
        return ResponseEntity.ok("Certification added successfully");
    }

    @PostMapping("project")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addProject(@RequestBody ProjectDTO projectDTO){
        projectService.addProject(projectDTO);
        return ResponseEntity.ok("Project added successfully");
    }

    @PostMapping("testimonial")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addTestimonial(@RequestBody TestimonialDTO testimonialDTO){
        testimonialService.addTestimonial(testimonialDTO);
        return ResponseEntity.ok("Testimonial added successfully");
    }
}
