package com.jolaar.personalwebsite.repository;

import com.jolaar.personalwebsite.model.PersonalInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PersonalInfoRepositoryTest {

    @Autowired
    PersonalInfoRepository personalInfoRepository;

    private PersonalInfo personalInfo1;
    private PersonalInfo personalInfo2;

    @BeforeEach
    public void setUp(){
        personalInfo1 = new PersonalInfo();
        personalInfo1.setFirstName("Alice");
        personalInfo1.setLastName("Smith");
        personalInfo1.setEmail("alice.smith@example.com");
        personalInfo1.setPhone("+1 555-1234");
        personalInfo1.setAddress("123 Main St, Springfield, IL");
        personalInfo1.setBio("Experienced software developer with a passion for web technologies.");
        personalInfo1.setLinkedinUrl("https://www.linkedin.com/in/alice-smith");
        personalInfo1.setGithubUrl("https://github.com/alicesmith");
        personalInfo1.setWebsiteUrl("https://www.alicesmith.com");

        personalInfo2 = new PersonalInfo();
        personalInfo2.setFirstName("Bob");
        personalInfo2.setLastName("Johnson");
        personalInfo2.setEmail("bob.johnson@example.com");
        personalInfo2.setPhone("+1 555-5678");
        personalInfo2.setAddress("456 Oak Ave, Springfield, IL");
        personalInfo2.setBio("Full-stack developer with experience in building scalable web applications.");
        personalInfo2.setLinkedinUrl("https://www.linkedin.com/in/bob-johnson");
        personalInfo2.setGithubUrl("https://github.com/bobjohnson");
        personalInfo2.setWebsiteUrl("https://www.bobjohnson.com");
    }

    @Test
    public void PersonalInfoRepositorySaveReturnsSavedPersonalInfo(){
        PersonalInfo savedPersonalInfo = personalInfoRepository.save(personalInfo1);
        assertNotNull(savedPersonalInfo);
        assertEquals(savedPersonalInfo.getId(), personalInfo1.getId());
        assertEquals(savedPersonalInfo.getFirstName(), personalInfo1.getFirstName());
        assertEquals(savedPersonalInfo.getLastName(), personalInfo1.getLastName());
        assertEquals(savedPersonalInfo.getEmail(), personalInfo1.getEmail());
        assertEquals(savedPersonalInfo.getPhone(), personalInfo1.getPhone());
        assertEquals(savedPersonalInfo.getAddress(), personalInfo1.getAddress());
        assertEquals(savedPersonalInfo.getBio(), personalInfo1.getBio());
        assertEquals(savedPersonalInfo.getLinkedinUrl(), personalInfo1.getLinkedinUrl());
        assertEquals(savedPersonalInfo.getGithubUrl(), personalInfo1.getGithubUrl());
        assertEquals(savedPersonalInfo.getWebsiteUrl(), personalInfo1.getWebsiteUrl());
    }

    @Test
    public void PersonalInfoRepositoryFindAllReturnsAllPersonalInfo(){
        personalInfoRepository.save(personalInfo1);
        personalInfoRepository.save(personalInfo2);
        List<PersonalInfo> savedPersonalInfos = personalInfoRepository.findAll();
        assertFalse(savedPersonalInfos.isEmpty());
        assertEquals(2, savedPersonalInfos.size());
        assertEquals(personalInfo1, savedPersonalInfos.getFirst());
        assertEquals(personalInfo2, savedPersonalInfos.getLast());
    }
}
