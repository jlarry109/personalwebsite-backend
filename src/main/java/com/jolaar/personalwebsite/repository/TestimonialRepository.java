package com.jolaar.personalwebsite.repository;

import com.jolaar.personalwebsite.model.Project;
import com.jolaar.personalwebsite.model.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {}
