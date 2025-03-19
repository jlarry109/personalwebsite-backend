package com.jolaar.personalwebsite.repository;

import com.jolaar.personalwebsite.common.enums.ProficiencyLevel;
import com.jolaar.personalwebsite.model.Skill;
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
public class SkillRepositoryTest {

    @Autowired
    private SkillRepository skillRepository;
    private Skill skill1;
    private Skill skill2;

    @BeforeEach
    public void setUp(){
        skill1 = new Skill();
        skill1.setSkillName("Java");
        skill1.setProficiencyLevel(ProficiencyLevel.EXPERT);

        skill2 = new Skill();
        skill2.setSkillName("JavaScript");
        skill2.setProficiencyLevel(ProficiencyLevel.INTERMEDIATE);
    }

    @Test
    public void SkillRepositorySaveReturnsSavedSkill(){
        Skill savedSkill = skillRepository.save(skill1);
        assertNotNull(savedSkill);
        assertEquals(skill1, savedSkill);
    }

    @Test
    public void SkillRepositoryFindAllReturnsSavedAllSkills(){
        skillRepository.save(skill1);
        skillRepository.save(skill2);
        List<Skill> savedSkills = skillRepository.findAll();

        assertFalse(savedSkills.isEmpty());
        assertEquals(skill1, savedSkills.getFirst());
        assertEquals(skill2, savedSkills.getLast());
    }
}
