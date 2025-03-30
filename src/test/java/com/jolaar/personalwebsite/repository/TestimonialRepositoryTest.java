package com.jolaar.personalwebsite.repository;

import com.jolaar.personalwebsite.model.Testimonial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TestimonialRepositoryTest {

    @Autowired
    private TestimonialRepository testimonialRepository;

    private Testimonial testimonial1;
    private Testimonial testimonial2;

    @BeforeEach
    public void setUp(){
        testimonial1 = new Testimonial();
        testimonial1.setName("John Doe");
        testimonial1.setMessage("Great work on the project! The website is easy to use and very responsive.");
        testimonial1.setRole("Product Manager");
        testimonial1.setOrganization("Tech Corp");
        testimonial1.setSubmittedAt(LocalDate.MAX);

        testimonial2 = new Testimonial();
        testimonial2.setName("Jane Smith");
        testimonial2.setMessage("Fantastic experience working with you! The implementation exceeded our expectations.");
        testimonial2.setRole("Lead Developer");
        testimonial2.setOrganization("Innovate Solutions");
        testimonial2.setSubmittedAt(LocalDate.MAX);
    }

    @Test
    public void TestimonialRepositorySaveReturnsSavedTestimonial(){
        Testimonial savedTestimonial = testimonialRepository.save(testimonial1);
        assertNotNull(savedTestimonial);
        assertEquals(testimonial1, savedTestimonial);
    }

    @Test
    public void TestimonialRepositoryFindAllReturnsSavedAllTestimonials(){
        testimonialRepository.save(testimonial1);
        testimonialRepository.save(testimonial2);
        List<Testimonial> savedTestimonials = testimonialRepository.findAll();

        assertFalse(savedTestimonials.isEmpty());
        assertEquals(testimonial1, savedTestimonials.getFirst());
        assertEquals(testimonial2, savedTestimonials.getLast());
    }
}
