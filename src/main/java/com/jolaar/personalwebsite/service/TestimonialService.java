package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.TestimonialDTO;
import com.jolaar.personalwebsite.mapper.TestimonialMapper;
import com.jolaar.personalwebsite.model.Testimonial;
import com.jolaar.personalwebsite.repository.TestimonialRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestimonialService {
    private final TestimonialRepository testimonialRepository;

    public TestimonialService(TestimonialRepository testimonialRepository){
        this.testimonialRepository = testimonialRepository;
    }


    public List<TestimonialDTO> getTestimonials() {
        List<Testimonial> testimonials = testimonialRepository.findAll();
        if(testimonials.isEmpty()){
            throw new ResourceNotFoundException("No testimonials found");
        }
        return testimonials.stream()
                .map(TestimonialMapper.INSTANCE::toDTO)
                .sorted(Comparator.comparing(TestimonialDTO::getSubmittedAt).reversed())
                .collect(Collectors.toUnmodifiableList());
    }

    public TestimonialDTO addTestimonial(TestimonialDTO testimonialDTO) {
        Testimonial testimonial = TestimonialMapper.INSTANCE.toEntity(testimonialDTO);

        Testimonial savedTestimonial = testimonialRepository.save(testimonial);

        return TestimonialMapper.INSTANCE.toDTO(savedTestimonial);
    }

}
