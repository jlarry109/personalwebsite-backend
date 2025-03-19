package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.SkillDTO;
import com.jolaar.personalwebsite.mapper.SkillMapper;
import com.jolaar.personalwebsite.model.Skill;
import com.jolaar.personalwebsite.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    public List<SkillDTO> getSkills() {
        List<Skill> skills = skillRepository.findAll();
        if(skills.isEmpty()){
            throw new ResourceNotFoundException("No skills to show");
        }
        return skills.stream()
                .map(SkillMapper.INSTANCE::toDTO)
                .sorted(Comparator.comparing(SkillDTO::getSkillName))
                .collect(Collectors.toUnmodifiableList());
    }

    public SkillDTO addSkill(SkillDTO skillDTO) {
        Skill skill = SkillMapper.INSTANCE.toEntity(skillDTO);

        Skill savedSkill = skillRepository.save(skill);

        return SkillMapper.INSTANCE.toDTO(savedSkill);
    }

}
