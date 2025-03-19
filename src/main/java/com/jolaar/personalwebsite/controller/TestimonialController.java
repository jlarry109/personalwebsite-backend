package com.jolaar.personalwebsite.controller;

import com.jolaar.personalwebsite.dto.TestimonialDTO;
import com.jolaar.personalwebsite.service.TestimonialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class TestimonialController {
    private final TestimonialService testimonialService;

    public  TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @GetMapping("testimonial")
    public ResponseEntity<List<TestimonialDTO>> getTestimonials(){
        return ResponseEntity.ok(testimonialService.getTestimonials());
    }
}
