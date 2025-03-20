package com.jolaar.personalwebsite.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


public class TestimonialDTO {
    private Long id;
    private String name;
    private String message;
    private String role;
    private String organization;
    private LocalDate submittedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public LocalDate getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDate submittedAt) {
        this.submittedAt = submittedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestimonialDTO that = (TestimonialDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(message, that.message) && Objects.equals(role, that.role) && Objects.equals(organization, that.organization) && Objects.equals(submittedAt, that.submittedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, message, role, organization, submittedAt);
    }
}

