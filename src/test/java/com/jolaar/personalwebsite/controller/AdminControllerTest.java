package com.jolaar.personalwebsite.controller;

import com.jolaar.personalwebsite.common.enums.ProficiencyLevel;
import com.jolaar.personalwebsite.dto.*;
import com.jolaar.personalwebsite.model.Admin;
import com.jolaar.personalwebsite.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;

    @Mock
    private ExperienceService experienceService;

    @Mock
    private EducationService educationService;

    @Mock
    private SkillService skillService;

    @Mock
    private CertificationService certificationService;

    @Mock
    private ProjectService projectService;

    @Mock
    private TestimonialService testimonialService;

    @Mock
    private CertificationDTO certification;

    @Mock
    private PersonalInfoService personalInfoService;

    private ExperienceDTO experience;
    private ExperienceDetailDTO detail1;
    private ExperienceDetailDTO detail2;
    private EducationDTO education;
    private PersonalInfoDTO personalInfo;
    private ProjectDTO project;
    private SkillDTO skill;
    private TestimonialDTO testimonial;

    @BeforeEach
    public void setup() {
        detail1 = new ExperienceDetailDTO("Led a team of 5 developers");
        detail2 = new ExperienceDetailDTO("Developed a new feature for the product");

        // Create the ExperienceDTO object with the sample data
        experience = new ExperienceDTO(
                1L,  // id
                "Tech Solutions Inc.",  // companyName
                "Software Engineer",  // position
                LocalDate.of(2019, 1, 1),  // startDate
                LocalDate.of(2022, 12, 31),  // endDate
                "Responsible for software development, leading projects, and mentoring juniors.",  // description
                List.of(detail1, detail2)  // details - list of ExperienceDetailDTO
        );

        education = new EducationDTO();
        education.setSchoolName("University of Example");
        education.setDegree("Bachelor of Science in Computer Science");
        education.setStartYear(2015);
        education.setEndYear(2019);

        personalInfo = new PersonalInfoDTO();
        personalInfo.setFirstName("John");
        personalInfo.setLastName("Doe");
        personalInfo.setEmail("john.doe@example.com");
        personalInfo.setPhone("+1234567890");
        personalInfo.setAddress("123 Main St, Anytown, USA");
        personalInfo.setBio("Software developer with a passion for coding and technology.");
        personalInfo.setLinkedinUrl("https://www.linkedin.com/in/johndoe");
        personalInfo.setGithubUrl("https://github.com/johndoe");
        personalInfo.setWebsiteUrl("https://www.johndoe.com");

        project = new ProjectDTO();
        project.setTitle("Personal Portfolio");
        project.setDescription("A personal portfolio website to showcase projects and skills.");
        project.setGithubLink("https://github.com/johndoe/portfolio");
        project.setLiveDemoLink("https://www.johndoeportfolio.com");
        project.setDateCreated(LocalDate.of(2023, 5, 1));

        skill = new SkillDTO();
        skill.setSkillName("Java Programming");
        skill.setProficiencyLevel(ProficiencyLevel.EXPERT);

        LocalDateTime currentDateTime = LocalDateTime.now();
        testimonial = new TestimonialDTO();
        testimonial.setName("John Doe");
        testimonial.setMessage("Great work on the project. I really appreciate the effort put in.");
        testimonial.setRole("Project Manager");
        testimonial.setOrganization("Tech Solutions Inc.");
        testimonial.setSubmittedAt(currentDateTime);

        certification = new CertificationDTO();
        certification.setCertName("Java Programming Certification");
        certification.setIssuingOrg("Oracle");
        certification.setDateEarned(LocalDate.of(2023, 5, 15));
        certification.setCertUrl("https://www.oracle.com/certifications/java-programming");
    }

    @Test
    public void testRegisterAdmin() {
        AuthRequest adminRequest = new AuthRequest();
        adminRequest.setUsername("newAdmin");
        adminRequest.setPassword("password");

        Admin newAdmin = new Admin();
        newAdmin.setUsername("newAdmin");
        newAdmin.setPassword("password");
        when(adminService.registerAdmin(any(AuthRequest.class))).thenReturn(newAdmin);


        ResponseEntity<?> response = adminController.registerAdmin(adminRequest);

        assertEquals("Admin registered: newAdmin", response.getBody());
        verify(adminService, times(1)).registerAdmin(any(AuthRequest.class));
    }

    @Test
    public void testAddExperience() {
        doReturn(experience).when(experienceService).addExperience(any(ExperienceDTO.class));

        ResponseEntity<String> response = adminController.addExperience(experience);

        assertEquals("Experience added successfully", response.getBody());
        verify(experienceService, times(1)).addExperience(any(ExperienceDTO.class));
    }

    @Test
    public void testUpdateExperience() {
        ExperienceDTO updatedExperience = new ExperienceDTO(
                1L,
                "Tech Solutions Inc.",
                "Software Engineer",
                LocalDate.of(2022, 9, 17),
                LocalDate.of(2024, 11, 12),
                "Responsible for software development, leading projects, and mentoring juniors.",  // description
                List.of()
        );
        doReturn(updatedExperience).when(experienceService).updateExperience(updatedExperience.id(), updatedExperience);

        ResponseEntity<?> response = adminController.updateExperience(updatedExperience.id(), updatedExperience);
        assertNotNull(response);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("Experience updated successfully", response.getBody());

        verify(experienceService, times(1)).updateExperience(updatedExperience.id(), updatedExperience);
    }

    @Test
    public void testDeleteExperience(){
        doNothing().when(experienceService).deleteExperience(experience.id());

        ResponseEntity<?> response = adminController.deleteExperience(experience.id());
        assertNotNull(response);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("Experience deleted successfully", response.getBody());
    }

    @Test
    public void testAddEducation(){
        doReturn(education).when(educationService).addEducation(any(EducationDTO.class));
        ResponseEntity<?> response = adminController.addEducation(education);
        assertNotNull(response);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("Education added successfully", response.getBody());
    }

    @Test
    public void testPersonalInfo(){
        doReturn(personalInfo).when(personalInfoService).addPersonalInfo(any(PersonalInfoDTO.class));
        ResponseEntity<?> response = adminController.addPersonalInfo(personalInfo);
        assertNotNull(response);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("Personal Info added successfully", response.getBody());
    }

    @Test
    public void testAddSkill(){
        doReturn(skill).when(skillService).addSkill(any(SkillDTO.class));
        ResponseEntity<?> response = adminController.addSkill(skill);
        assertNotNull(response);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("Skill added successfully", response.getBody());
    }

    @Test
    public void testAddCertification(){
        doReturn(certification).when(certificationService).addCertification(any(CertificationDTO.class));
        ResponseEntity<?> response = adminController.addCertification(certification);
        assertNotNull(response);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("Certification added successfully", response.getBody());
    }

    @Test
    public void testAddProject(){
        doReturn(project).when(projectService).addProject(any(ProjectDTO.class));
        ResponseEntity<?> response = adminController.addProject(project);
        assertNotNull(response);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("Project added successfully", response.getBody());
    }

    @Test
    public void testAddTestimonial(){
        doReturn(testimonial).when(testimonialService).addTestimonial(any(TestimonialDTO.class));
        ResponseEntity<?> response = adminController.addTestimonial(testimonial);
        assertNotNull(response);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("Testimonial added successfully", response.getBody());
    }
}
