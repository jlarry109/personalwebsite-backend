package com.jolaar.personalwebsite.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "awards")
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String organization;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateReceived;

    // Getters and Setters
}

