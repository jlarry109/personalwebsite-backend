package com.jolaar.personalwebsite.repository;
import com.jolaar.personalwebsite.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {}
