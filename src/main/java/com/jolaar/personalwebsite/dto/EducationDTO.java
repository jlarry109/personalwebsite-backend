package com.jolaar.personalwebsite.dto;


import lombok.Data;

import java.util.Objects;

@Data
public class EducationDTO {
    private Long id;
    private String schoolName;
    private String degree;
    private Integer startYear;
    private Integer endYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationDTO that = (EducationDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(schoolName, that.schoolName) && Objects.equals(degree, that.degree) && Objects.equals(startYear, that.startYear) && Objects.equals(endYear, that.endYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schoolName, degree, startYear, endYear);
    }
}

