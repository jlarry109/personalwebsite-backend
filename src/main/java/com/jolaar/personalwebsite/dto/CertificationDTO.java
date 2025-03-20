package com.jolaar.personalwebsite.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class CertificationDTO {
    private Long id;
    private String certName;
    private String issuingOrg;
    private LocalDate dateEarned;
    private String certUrl;

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
        CertificationDTO that = (CertificationDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(certName, that.certName) && Objects.equals(issuingOrg, that.issuingOrg) && Objects.equals(dateEarned, that.dateEarned) && Objects.equals(certUrl, that.certUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, certName, issuingOrg, dateEarned, certUrl);
    }
}
