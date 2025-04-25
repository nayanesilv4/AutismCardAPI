package com.autismcard.autismcard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @Column(length = 11)
    @NotNull
    private String cpf;

    @NotBlank
    private String fullName;

    @NotNull
    private LocalDate birthDate;

    @NotBlank
    private String cid;

    @NotBlank
    private String phone;

    @NotBlank
    private String reportLink;

    @NotBlank
    private String responsibleName;

    @NotBlank
    private String responsiblePhone;

    // Construtores


    public Card() {
    }

    public Card(String fullName, LocalDate birthDate, String cid, String phone, String reportLink, String responsibleName, String responsiblePhone) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.cid = cid;
        this.phone = phone;
        this.reportLink = reportLink;
        this.responsibleName = responsibleName;
        this.responsiblePhone = responsiblePhone;
    }

    // Getters e Setters


    public @NotNull String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank String fullName) {
        this.fullName = fullName;
    }

    public @NotNull LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@NotNull LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public @NotBlank String getCid() {
        return cid;
    }

    public void setCid(@NotBlank String cid) {
        this.cid = cid;
    }

    public @NotBlank String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank String phone) {
        this.phone = phone;
    }

    public @NotBlank String getReportLink() {
        return reportLink;
    }

    public void setReportLink(@NotBlank String reportLink) {
        this.reportLink = reportLink;
    }

    public @NotBlank String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(@NotBlank String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public @NotBlank String getResponsiblePhone() {
        return responsiblePhone;
    }

    public void setResponsiblePhone(@NotBlank String responsiblePhone) {
        this.responsiblePhone = responsiblePhone;
    }
}
