package com.jolaar.personalwebsite.model;

import com.jolaar.personalwebsite.common.enums.ProficiencyLevel;
import com.jolaar.personalwebsite.common.enums.SkillCategory;
import jakarta.persistence.*;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skillName;

    @Enumerated(EnumType.STRING)
    private ProficiencyLevel proficiencyLevel;

    @ElementCollection(targetClass = SkillCategory.class)
    @Enumerated(EnumType.STRING)
    private List<SkillCategory> skillCategories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public ProficiencyLevel getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(ProficiencyLevel proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public List<SkillCategory> getSkillCategories() {
        return skillCategories;
    }

    public void setSkillCategories(List<SkillCategory> skillCategories) {
        this.skillCategories = skillCategories.stream().toList();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) && Objects.equals(skillName, skill.skillName) && proficiencyLevel == skill.proficiencyLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skillName, proficiencyLevel);
    }
}

