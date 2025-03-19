package com.jolaar.personalwebsite.repository;

import com.jolaar.personalwebsite.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {}
