package com.jolaar.personalwebsite.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.jolaar.personalwebsite.common.config.JacksonConfig;
import com.jolaar.personalwebsite.common.enums.ProficiencyLevel;
import com.jolaar.personalwebsite.common.enums.Role;
import com.jolaar.personalwebsite.common.enums.SkillCategory;
import com.jolaar.personalwebsite.dto.*;
import com.jolaar.personalwebsite.model.Admin;
import com.jolaar.personalwebsite.model.StandardUser;
import com.jolaar.personalwebsite.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class AdminControllerIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private AdminService adminService;

    @Mock
    private CertificationService certificationService;

    @Mock
    private ExperienceService experienceService;

    @Mock
    private EducationService educationService;

    @Mock
    private PersonalInfoService personalInfoService;

    @Mock
    private ProjectService projectService;

    @Mock
    private SkillService skillService;

    @Mock
    private TestimonialService testimonialService;

    @InjectMocks
    private AdminController adminController;

    ObjectMapper objectMapper;
    private AuthRequest authRequest;
    private Admin admin;
    private StandardUser standardUser;
    private ExperienceDTO experience;
    private ExperienceDetailDTO detail1;
    private ExperienceDetailDTO detail2;
    private EducationDTO education1;
    private EducationDTO education2;
    private PersonalInfoDTO personalInfo;
    private ProjectDTO project;
    private SkillDTO skill;
    private TestimonialDTO testimonial;
    private LocalDate currentDateTime;
    private CertificationDTO certification;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
        objectMapper = new JacksonConfig().objectMapper();

        admin = new Admin();
        admin.setUsername("adminUser");
        admin.setPassword("adminPassword123");
        admin.setRole(Role.ADMIN);
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setEmail("admin@example.com");

        standardUser = new StandardUser();
        standardUser.setUsername("standardUser");
        standardUser.setPassword("userPassword123");
        standardUser.setRole(Role.STANDARD_USER);
        standardUser.setFirstName("John");
        standardUser.setLastName("Doe");
        standardUser.setEmail("user@example.com");

        authRequest = new AuthRequest();
        authRequest.setUsername("adminUser");
        authRequest.setPassword("adminPassword123");
        authRequest.setFirstName("Admin");
        authRequest.setLastName("User");
        authRequest.setEmail("admin@example.com");

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

        education1 = new EducationDTO();
        education1.setSchoolName("University of Example");
        education1.setDegree("Bachelor of Science in Computer Science");
        education1.setStartYear(2015);
        education1.setEndYear(2019);

        // Create the second EducationDTO object
        education2 = new EducationDTO();
        education2.setSchoolName("Example State College");
        education2.setDegree("Master of Science in Software Engineering");
        education2.setStartYear(2020);
        education2.setEndYear(2022);

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
        skill.setSkillCategories(List.of(SkillCategory.PROGRAMMING_LANGUAGES));

        testimonial = new TestimonialDTO();
        currentDateTime = LocalDate.now();
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
    @WithMockUser(roles = "ADMIN")  // Mock an authenticated user with ADMIN role
    public void testRegisterAdmin() throws Exception {
        // Mock the service behavior
        when(adminService.registerAdmin(authRequest)).thenReturn(admin);

        // Perform the request and validate the response
        mockMvc.perform(post("/api/admin/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk())  // Expect HTTP 200 OK
                .andExpect(jsonPath("$").value("Admin registered: adminUser"));  // Check the response message

        verify(adminService).registerAdmin(authRequest);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void AdminControllerAddCertificationReturnsCertificationDTO() throws Exception {
        when(certificationService.addCertification(any(CertificationDTO.class))).thenReturn(certification);

        mockMvc.perform(post("/api/admin/certification")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(certification)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Certification added successfully"));

        verify(certificationService).addCertification(certification);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void AdminControllerAddExperienceReturnsExperienceDTO() throws Exception {
        when(experienceService.addExperience(any(ExperienceDTO.class))).thenReturn(experience);

        mockMvc.perform(post("/api/admin/experience")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(experience)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Experience added successfully"))
                .andReturn();

        verify(experienceService).addExperience(experience);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void AdminControllerUpdateExperienceReturnsUpdatedExperienceDTO() throws Exception {
        ExperienceDTO updatedExperience = new ExperienceDTO(
                1L,  // id
                "Tech Solutions Inc.",  // companyName
                "Software Engineer",  // position
                LocalDate.of(2022, 9, 17),  // startDate
                LocalDate.of(2024, 11, 12),  // endDate
                "Responsible for software development, leading projects, and mentoring juniors.",  // description
                List.of()
        );
        when(experienceService.updateExperience(experience.id(), updatedExperience)).thenReturn(updatedExperience);

        mockMvc.perform(put("/api/admin/experience/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedExperience)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Experience updated successfully"));

        verify(experienceService).updateExperience(experience.id(), updatedExperience);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void AdminControllerDeleteExperienceSuccess() throws Exception {
        doNothing().when(experienceService).deleteExperience(anyLong());

        mockMvc.perform(delete("/api/admin/experience/{id}", experience.id())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Experience deleted successfully"));

        verify(experienceService).deleteExperience(experience.id());
    }

//    @Test
//    @WithMockUser(roles = "ADMIN")
//    public void AdminControllerDeleteExperienceNotFound() throws Exception {
//
//        doThrow(new ResourceNotFoundException("Experience not found")).when(experienceService).deleteExperience(experience.id());
//
//        // Act & Assert: Perform the DELETE request and check the status and error message in the response
//
//            mockMvc.perform(delete("/api/admin/experience/{id}", experience.id())
//                            .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isNotFound())  // Expect HTTP 404 Not Found
//                    .andExpect(jsonPath("$").value("Experience not found"))
//                    .andDo(print());  // Check the error message
//
//
//        // Verify that the service's delete method was called with the correct ID
//        verify(experienceService).deleteExperience(experience.id());
//    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void AdminControllerAddEducationReturnsEducationDTO() throws Exception {
        when(educationService.addEducation(any(EducationDTO.class))).thenReturn(education1);

        mockMvc.perform(post("/api/admin/education")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(education1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Education added successfully"));

        verify(educationService).addEducation(education1);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void AdminControllerAddPersonalInfoReturnsPersonalInfoDTO() throws Exception{
        when(personalInfoService.addPersonalInfo(any(PersonalInfoDTO.class))).thenReturn(personalInfo);

        mockMvc.perform(post("/api/admin/personal-info")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personalInfo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Personal Info added successfully"));

        verify(personalInfoService).addPersonalInfo(personalInfo);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void AdminControllerAddProjectReturnsProjectDTO() throws Exception {
        when(projectService.addProject(any(ProjectDTO.class))).thenReturn(project);

        mockMvc.perform(post("/api/admin/project")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Project added successfully"));

        verify(projectService).addProject(project);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void AdminControllerAddSkillReturnsSkillDTO() throws Exception{
        when(skillService.addSkill(any(SkillDTO.class))).thenReturn(skill);

        mockMvc.perform(post("/api/admin/skill")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skill)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Skill added successfully"));

        verify(skillService).addSkill(skill);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void AdminControllerAddTestimonialReturnsTestimonialDTO() throws Exception{
        when(testimonialService.addTestimonial(any(TestimonialDTO.class))).thenReturn(testimonial);

        mockMvc.perform(post("/api/admin/testimonial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testimonial)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Testimonial added successfully"));

        verify(testimonialService).addTestimonial(testimonial);
    }
}
