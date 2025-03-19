package com.jolaar.personalwebsite.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class ExperienceDetailDTO {
    private Long id;
    private String taskDescription;

    public ExperienceDetailDTO() {
    }

    public ExperienceDetailDTO(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Long getId() {
        return id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienceDetailDTO that = (ExperienceDetailDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(taskDescription, that.taskDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskDescription);
    }
}

