package com.jolaar.personalwebsite.mapper;

import com.jolaar.personalwebsite.common.enums.SkillCategory;
import com.jolaar.personalwebsite.dto.SkillDTO;
import com.jolaar.personalwebsite.model.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface SkillMapper {
    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    SkillDTO toDTO (Skill skill);

    Skill toEntity(SkillDTO skillDTO);


    default List<SkillCategory> mapSkillCategoryList(List<String> categories) {
        return categories.stream()
                .map(SkillCategory::valueOf)
                .collect(Collectors.toList());
    }
}
