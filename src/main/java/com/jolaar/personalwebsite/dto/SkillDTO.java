package com.jolaar.personalwebsite.dto;

import com.jolaar.personalwebsite.common.enums.ProficiencyLevel;
import com.jolaar.personalwebsite.common.enums.SkillCategory;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class SkillDTO {
    private Long id;
    private String skillName;
    private ProficiencyLevel proficiencyLevel;
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
        SkillDTO skillDTO = (SkillDTO) o;
        return Objects.equals(id, skillDTO.id) && Objects.equals(skillName, skillDTO.skillName) && proficiencyLevel == skillDTO.proficiencyLevel && Objects.equals(skillCategories, skillDTO.skillCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skillName, proficiencyLevel, skillCategories);
    }
}
