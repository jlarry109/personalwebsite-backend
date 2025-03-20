package com.jolaar.personalwebsite.common.util;

import com.jolaar.personalwebsite.common.enums.ProficiencyLevel;
import com.jolaar.personalwebsite.common.enums.SkillCategory;
import com.jolaar.personalwebsite.model.*;
import com.jolaar.personalwebsite.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final PersonalInfoRepository personalInfoRepository;
    private final ProjectRepository projectRepository;
    private final ExperienceRepository experienceRepository;
    private final EducationRepository educationRepository;
    private final SkillRepository skillRepository;
    private final CertificationRepository certificationRepository;
    private final TestimonialRepository testimonialRepository;

    public DataLoader(PersonalInfoRepository personalInfoRepository, ProjectRepository projectRepository, ExperienceRepository experienceRepository,
                      EducationRepository educationRepository, SkillRepository skillRepository, CertificationRepository certificationRepository,
                      TestimonialRepository testimonialRepository) {
        this.personalInfoRepository = personalInfoRepository;
        this.projectRepository = projectRepository;
        this.experienceRepository = experienceRepository;
        this.educationRepository = educationRepository;
        this.skillRepository = skillRepository;
        this.certificationRepository = certificationRepository;
        this.testimonialRepository = testimonialRepository;
    }

    @Override
    public void run(String... args) {

        //Add PersonalInfo
        if (personalInfoRepository.count() == 0) { // Prevent duplicate inserts
            PersonalInfo personalInfo = new PersonalInfo();
            personalInfo.setFirstName("Jones");
            personalInfo.setLastName("Larry");
            personalInfo.setEmail("joxlaar@gmail.com");
            personalInfo.setPhone("123-456-7890");
            personalInfo.setAddress("401 Chapel Dr, Durham, NC 27705, USA");
            personalInfo.setBio("Machine Learning SDE with 2 years of experience.");
            personalInfo.setLinkedinUrl("https://linkedin.com/in/joxlaar");
            personalInfo.setGithubUrl("https://github.com/joxlaar");
            personalInfo.setWebsiteUrl("https://joxlaar.dev");

            personalInfoRepository.save(personalInfo);
        }
        // Add Education
        if (educationRepository.count() == 0) {
            Education education1 = new Education();
            education1.setSchoolName("Duke University");
            education1.setDegree("Master of Science in Electrical and Computer Engineering");
            education1.setCity("Manchester");
            education1.setProvince("Greater Manchester");
            education1.setCountry("United Kingdom");
            education1.setStartYear(2022);
            education1.setEndYear(2024);

            Education education2 = new Education();
            education2.setSchoolName("University of Manchester");
            education2.setCity("Durham");
            education2.setProvince("North Carolina");
            education2.setCountry("United States");
            education2.setDegree("Masters in Biomedical Engineering");
            education2.setStartYear(2019);
            education2.setEndYear(2020);

            educationRepository.saveAll(List.of(education1, education2));
        }

//        Add Experience
        if (experienceRepository.count() == 0) { // Prevent duplicate inserts
            Experience experience1 = new Experience();
            experience1.setCompanyName("TechCorp");
            experience1.setPosition("Software Engineer");
            experience1.setStartDate(LocalDate.of(2019, 6, 1));
            experience1.setEndDate(LocalDate.of(2022, 12, 31));
            experience1.setDescription("Developed and maintained web applications using Java and Spring Boot.");

            Experience experience2 = new Experience();
            experience2.setCompanyName("InnovateSoft");
            experience2.setPosition("Software Engineer");
            experience2.setStartDate(LocalDate.of(2023, 1, 1));
            experience2.setEndDate(LocalDate.now()); // Currently employed
            experience2.setDescription("Leading a team of developers and working on scalable microservices architecture.");

            experienceRepository.saveAll(List.of(experience1, experience2));
        }

        // Add Skills
        if (skillRepository.count() == 0) {

            // Might want to sort skills into soft skills vs technical skills vs Software Design & Architecture vs IDE & Tools, vs testing, vs version control, Cloud, DevOps, Databases, Backend Technologies, Web Technologies, Programming Languages, etc

            // Programming Languages
            Skill javaSkill = new Skill();
            javaSkill.setSkillName("Java");
            javaSkill.setProficiencyLevel(ProficiencyLevel.ADVANCED);
            javaSkill.setSkillCategories(List.of(SkillCategory.PROGRAMMING_LANGUAGES));

            Skill cSkill = new Skill();
            cSkill.setSkillName("C");
            cSkill.setProficiencyLevel(ProficiencyLevel.ADVANCED);
            cSkill.setSkillCategories(List.of(SkillCategory.PROGRAMMING_LANGUAGES));

            Skill pythonSkill = new Skill();
            pythonSkill.setSkillName("Python");
            pythonSkill.setProficiencyLevel(ProficiencyLevel.INTERMEDIATE);
            pythonSkill.setSkillCategories(List.of(SkillCategory.PROGRAMMING_LANGUAGES));

            Skill cppSkill = new Skill();
            cppSkill.setSkillName("C++");
            cppSkill.setProficiencyLevel(ProficiencyLevel.INTERMEDIATE);
            cppSkill.setSkillCategories(List.of(SkillCategory.PROGRAMMING_LANGUAGES));

            Skill sqlSkill = new Skill();
            sqlSkill.setSkillName("SQL");
            sqlSkill.setProficiencyLevel(ProficiencyLevel.ADVANCED);
            sqlSkill.setSkillCategories(List.of(SkillCategory.PROGRAMMING_LANGUAGES));

            // Cloud and Devops
            Skill awsSkill = new Skill();
            awsSkill.setSkillName("AWS");
            awsSkill.setProficiencyLevel(ProficiencyLevel.EXPERT);
            awsSkill.setSkillCategories(List.of(SkillCategory.CLOUD, SkillCategory.DEVOPS));

            Skill dockerSkill = new Skill();
            dockerSkill.setSkillName("Docker");
            dockerSkill.setProficiencyLevel(ProficiencyLevel.ADVANCED);
            dockerSkill.setSkillCategories(List.of(SkillCategory.CONTAINERIZATION, SkillCategory.DEVOPS));

            Skill cicdSkill = new Skill();
            cicdSkill.setSkillName("CI/CD");
            cicdSkill.setProficiencyLevel(ProficiencyLevel.ADVANCED);
            cicdSkill.setSkillCategories(List.of(SkillCategory.DEVOPS));

            // Databases
            Skill mysqlSkill = new Skill();
            mysqlSkill.setSkillName("MySQL");
            mysqlSkill.setProficiencyLevel(ProficiencyLevel.INTERMEDIATE);

            Skill postgresqlSkill = new Skill();
            postgresqlSkill.setSkillName("PostgreSQL");
            postgresqlSkill.setProficiencyLevel(ProficiencyLevel.INTERMEDIATE);
            postgresqlSkill.setSkillCategories(List.of(SkillCategory.DATABASES));

            Skill mongodbSkill = new Skill();
            mongodbSkill.setSkillName("MongoDB");
            mongodbSkill.setProficiencyLevel(ProficiencyLevel.INTERMEDIATE);
            mongodbSkill.setSkillCategories(List.of(SkillCategory.DATABASES));

            Skill dynamodbSkill = new Skill();
            dynamodbSkill.setSkillName("DynamoDB");
            dynamodbSkill.setProficiencyLevel(ProficiencyLevel.INTERMEDIATE);
            dynamodbSkill.setSkillCategories(List.of(SkillCategory.DATABASES));

            // Version Control
            Skill gitSkill = new Skill();
            gitSkill.setSkillName("GIT");
            gitSkill.setProficiencyLevel(ProficiencyLevel.ADVANCED);
            gitSkill.setSkillCategories(List.of(SkillCategory.VERSION_CONTROL));

            // Testing
            Skill junitSkill = new Skill();
            junitSkill.setSkillName("JUnit");
            junitSkill.setProficiencyLevel(ProficiencyLevel.ADVANCED);
            junitSkill.setSkillCategories(List.of(SkillCategory.TESTING));

            Skill mockitoSkill = new Skill();
            mockitoSkill.setSkillName("Mockito");
            mockitoSkill.setProficiencyLevel(ProficiencyLevel.ADVANCED);
            mockitoSkill.setSkillCategories(List.of(SkillCategory.TESTING));

            // IDE & Tools
            Skill intellijSkill = new Skill();
            intellijSkill.setSkillName("IntelliJ IDEA");
            intellijSkill.setProficiencyLevel(ProficiencyLevel.INTERMEDIATE);
            intellijSkill.setSkillCategories(List.of(SkillCategory.IDE, SkillCategory.TOOLS));

            Skill eclipseSkill = new Skill();
            eclipseSkill.setSkillName("Eclipse");
            eclipseSkill.setProficiencyLevel(ProficiencyLevel.INTERMEDIATE);
            eclipseSkill.setSkillCategories(List.of(SkillCategory.IDE, SkillCategory.TOOLS));

            Skill vscodeSkill = new Skill();
            vscodeSkill.setSkillName("VS Code");
            vscodeSkill.setProficiencyLevel(ProficiencyLevel.INTERMEDIATE);
            vscodeSkill.setSkillCategories(List.of(SkillCategory.IDE, SkillCategory.TOOLS));

            Skill postmanSkill = new Skill(); // Tools
            postmanSkill.setSkillName("Postman");
            postmanSkill.setProficiencyLevel(ProficiencyLevel.INTERMEDIATE);
            postmanSkill.setSkillCategories(List.of(SkillCategory.TOOLS));

            skillRepository.saveAll(List.of(javaSkill, cSkill, pythonSkill, cppSkill, sqlSkill, awsSkill, dockerSkill,
                    cicdSkill, mysqlSkill, postgresqlSkill, mongodbSkill, dynamodbSkill, gitSkill, junitSkill, mockitoSkill,
                    intellijSkill, eclipseSkill, vscodeSkill, postmanSkill));

            /* Web Technologies:
Vue.js or React (for frontend development)
HTML5 & CSS3 (for building responsive and structured web pages)
RESTful APIs (Designing and consuming APIs)
*/

            /*
            Soft Skills:
Problem-Solving (Strong analytical skills to identify and fix issues)
Collaboration (Team player with a focus on cross-functional communication)
Agile Methodologies (Scrum, Kanban)
Time Management (Efficiently prioritize tasks and meet deadlines)
Adaptability (Quick to learn new technologies and tools)
Communication Skills (Clear documentation, presentations, and team collaboration)
Leadership (Experience mentoring junior developers, leading small teams)
            * */
        }

        // Add Projects
        if (projectRepository.count() == 0) {
            Project portfolioWebsite = new Project();
            portfolioWebsite.setTitle("Personal Portfolio Website");
            portfolioWebsite.setDescription("A personal portfolio website built with Java and Spring Boot to showcase skills, projects, and achievements.");
            portfolioWebsite.setGithubLink("https://github.com/yourusername/portfolio-website");
            portfolioWebsite.setLiveDemoLink("https://www.yourportfolio.com");
            portfolioWebsite.setDateCreated(LocalDate.of(2024, 1, 15));

            Project eCommercePlatform = new Project();
            eCommercePlatform.setTitle("E-Commerce Platform");
            eCommercePlatform.setDescription("An e-commerce platform with features such as product search, user authentication, and a shopping cart. Built using React, Node.js, and MongoDB.");
            eCommercePlatform.setGithubLink("https://github.com/yourusername/ecommerce-platform");
            eCommercePlatform.setLiveDemoLink("https://www.myecommerceplatform.com");
            eCommercePlatform.setDateCreated(LocalDate.of(2023, 7, 5));

            Project socialMediaApp = new Project();
            socialMediaApp.setTitle("Social Media App");
            socialMediaApp.setDescription("A social media app that allows users to share posts, like, comment, and follow other users. Developed using React Native and Firebase.");
            socialMediaApp.setGithubLink("https://github.com/yourusername/social-media-app");
            socialMediaApp.setLiveDemoLink("https://www.mysocialmediaapp.com");
            socialMediaApp.setDateCreated(LocalDate.of(2023, 10, 22));

            projectRepository.saveAll(List.of(portfolioWebsite, eCommercePlatform, socialMediaApp));
        }
        // Add Certifications
        if (certificationRepository.count() == 0) {
            Certification awsDeveloperAssociate = new Certification("AWS Certified Developer - Associate", "Amazon Web Services",
                    LocalDate.of(2023, 5, 20), "https://www.aws.com/certifications/developer-associate");

            Certification awsDevOps = new Certification("AWS Certified DevOps Engineer - Professional",
                    "Amazon Web Services", LocalDate.of(2024, 2, 15),
                    "https://www.aws.com/certifications/devops-engineer-professional");

            certificationRepository.saveAll(List.of(awsDeveloperAssociate, awsDevOps));
        }
        // Add Testimonials
        if (testimonialRepository.count() == 0) {
            Testimonial testimonial1 = new Testimonial();
            testimonial1.setName("John Doe");
            testimonial1.setMessage("This service is fantastic! It helped us scale our platform quickly and efficiently. Highly recommend it.");
            testimonial1.setRole("Senior Software Engineer");
            testimonial1.setOrganization("Tech Innovators Inc.");
            testimonial1.setSubmittedAt(LocalDate.of(2024, 3, 10));

            Testimonial testimonial2 = new Testimonial();
            testimonial2.setName("Jane Smith");
            testimonial2.setMessage("The level of professionalism and technical expertise is second to none. A great experience working with this team.");
            testimonial2.setRole("Product Manager");
            testimonial2.setOrganization("Creative Solutions Ltd.");
            testimonial2.setSubmittedAt(LocalDate.of(2024, 3, 15));

            testimonialRepository.saveAll(List.of(testimonial1, testimonial2));
        }

    }
}

