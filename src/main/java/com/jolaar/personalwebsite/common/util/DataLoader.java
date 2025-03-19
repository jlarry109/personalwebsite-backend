package com.jolaar.personalwebsite.common.util;

import com.jolaar.personalwebsite.common.enums.ProficiencyLevel;
import com.jolaar.personalwebsite.common.enums.SkillCategory;
import com.jolaar.personalwebsite.model.Education;
import com.jolaar.personalwebsite.model.Experience;
import com.jolaar.personalwebsite.model.PersonalInfo;
import com.jolaar.personalwebsite.model.Skill;
import com.jolaar.personalwebsite.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final PersonalInfoRepository personalInfoRepository;
    private final ExperienceRepository experienceRepository;
    private final EducationRepository educationRepository;
    private final SkillRepository skillRepository;
    private final CertificationRepository certificationRepository;
    private final TestimonialRepository testimonialRepository;

    public DataLoader(PersonalInfoRepository personalInfoRepository, ExperienceRepository experienceRepository,
                      EducationRepository educationRepository, SkillRepository skillRepository, CertificationRepository certificationRepository,
                      TestimonialRepository testimonialRepository) {
        this.personalInfoRepository = personalInfoRepository;
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
        // Add Certifications
        // Add Testimonials

    }
}

