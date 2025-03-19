package com.jolaar.personalwebsite.mapper;

import com.jolaar.personalwebsite.dto.TestimonialDTO;
import com.jolaar.personalwebsite.model.Testimonial;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TestimonialMapper {

    TestimonialMapper INSTANCE = Mappers.getMapper(TestimonialMapper.class);
    TestimonialDTO toDTO(Testimonial testimonial);

    Testimonial toEntity(TestimonialDTO testimonialDTO);
}
