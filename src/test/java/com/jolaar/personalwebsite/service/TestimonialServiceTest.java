package com.jolaar.personalwebsite.service;

import com.jolaar.personalwebsite.common.exception.ResourceNotFoundException;
import com.jolaar.personalwebsite.dto.SkillDTO;
import com.jolaar.personalwebsite.dto.TestimonialDTO;
import com.jolaar.personalwebsite.mapper.SkillMapper;
import com.jolaar.personalwebsite.mapper.TestimonialMapper;
import com.jolaar.personalwebsite.model.Skill;
import com.jolaar.personalwebsite.model.Testimonial;
import com.jolaar.personalwebsite.repository.TestimonialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestimonialServiceTest {
    @Mock
    private TestimonialRepository testimonialRepository;

    @InjectMocks
    private TestimonialService testimonialService;

    private TestimonialDTO testimonial1;
    private TestimonialDTO testimonial2;

    @BeforeEach
    public void setUp(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        testimonial1 = new TestimonialDTO();
        testimonial1.setName("John Doe");
        testimonial1.setMessage("Great work on the project. I really appreciate the effort put in.");
        testimonial1.setRole("Project Manager");
        testimonial1.setOrganization("Tech Solutions Inc.");
        testimonial1.setSubmittedAt(currentDateTime);

        LocalDateTime maxDateTime = LocalDateTime.MAX;
        testimonial2 = new TestimonialDTO();
        testimonial2.setName("Jane Smith");
        testimonial2.setMessage("Amazing teamwork and excellent results! Highly recommend.");
        testimonial2.setRole("Lead Developer");
        testimonial2.setOrganization("Innovative Software Co.");
        testimonial2.setSubmittedAt(maxDateTime);
    }

    @Test
    public void TestimonialServiceGetTestimonialThrowsResourceNotFoundException(){
        when(testimonialRepository.findAll()).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, ()-> testimonialService.getTestimonials());
        verify(testimonialRepository, times(1)).findAll();
    }

    @Test
    public void TestimonialServiceGetTestimonialReturnsTestimonialDTOs(){
        when(testimonialRepository.findAll()).thenReturn(
                List.of(TestimonialMapper.INSTANCE.toEntity(testimonial1), TestimonialMapper.INSTANCE.toEntity(testimonial2)));
        List<TestimonialDTO> returnedTestimonial = testimonialService.getTestimonials();
        List<TestimonialDTO> expectedSkills = Stream.of(testimonial1, testimonial2)
                .sorted(Comparator.comparing(TestimonialDTO::getSubmittedAt).reversed())
                .toList();
        assertFalse(returnedTestimonial.isEmpty());
        assertEquals(expectedSkills.getFirst(), returnedTestimonial.getFirst());
        assertEquals(expectedSkills.getLast(), returnedTestimonial.getLast());
        verify(testimonialRepository, times(1)).findAll();
    }

    @Test
    public void TestimonialServiceAddTestimonialReturnsTestimonialDTO(){
        when(testimonialRepository.save(any(Testimonial.class))).thenReturn(TestimonialMapper.INSTANCE.toEntity(testimonial2));

        TestimonialDTO savedTestimonial = testimonialService.addTestimonial(testimonial2);
        assertNotNull(savedTestimonial);
        assertEquals(testimonial2, savedTestimonial);
        verify(testimonialRepository, times(1)).save(any(Testimonial.class));
    }
}
