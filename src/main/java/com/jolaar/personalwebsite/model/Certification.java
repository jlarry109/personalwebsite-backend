package com.jolaar.personalwebsite.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "certifications")
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String certName;
    private String issuingOrg;
    private LocalDate dateEarned;
    private String certUrl;

    public Certification() {}

    public Certification(String certName, String issuingOrg, LocalDate dateEarned, String certUrl) {
        this.certName = certName;
        this.issuingOrg = issuingOrg;
        this.dateEarned = dateEarned;
        this.certUrl = certUrl;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getIssuingOrg() {
        return issuingOrg;
    }

    public void setIssuingOrg(String issuingOrg) {
        this.issuingOrg = issuingOrg;
    }

    public LocalDate getDateEarned() {
        return dateEarned;
    }

    public void setDateEarned(LocalDate dateEarned) {
        this.dateEarned = dateEarned;
    }

    public String getCertUrl() {
        return certUrl;
    }

    public void setCertUrl(String certUrl) {
        this.certUrl = certUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certification that = (Certification) o;
        return Objects.equals(certName, that.certName) && Objects.equals(issuingOrg, that.issuingOrg) && Objects.equals(dateEarned, that.dateEarned) && Objects.equals(certUrl, that.certUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, certName, issuingOrg, dateEarned, certUrl);
    }
}

