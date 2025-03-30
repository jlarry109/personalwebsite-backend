package com.jolaar.personalwebsite.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public record ExperienceDTO(
        Long id,
        String companyName,
        String position,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,
        String description,
        List<ExperienceDetailDTO> details // Nested DTO to avoid exposing entity relationships
) {

    @Override
    public Long id(){
        return id;
    }

    @Override
    public String companyName() {
        return companyName;
    }

    @Override
    public String position() {
        return position;
    }

    @Override
    public LocalDate startDate() {
        return startDate;
    }

    @Override
    public LocalDate endDate() {
        return endDate;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public List<ExperienceDetailDTO> details() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienceDTO that = (ExperienceDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(position, that.position) && Objects.equals(endDate, that.endDate) && Objects.equals(companyName, that.companyName) && Objects.equals(description, that.description) && Objects.equals(startDate, that.startDate) && Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, position, startDate, endDate, description, details);
    }
}

