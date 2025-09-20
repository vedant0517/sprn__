package com.sushrutai.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * Vaccination record tracking for age-based immunization schedules
 * Follows Government of India's Universal Immunization Programme (UIP)
 */
@Entity
@Table(name = "vaccination_records")
public class VaccinationRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VaccineType vaccineType;
    
    @Column(nullable = false)
    private LocalDate scheduledDate;
    
    private LocalDate administeredDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VaccinationStatus status;
    
    @Size(max = 200)
    private String healthCenterName;
    
    @Size(max = 500)
    private String notes;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    // Constructors
    public VaccinationRecord() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = VaccinationStatus.SCHEDULED;
    }
    
    public VaccinationRecord(User user, VaccineType vaccineType, LocalDate scheduledDate) {
        this();
        this.user = user;
        this.vaccineType = vaccineType;
        this.scheduledDate = scheduledDate;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public VaccineType getVaccineType() { return vaccineType; }
    public void setVaccineType(VaccineType vaccineType) { this.vaccineType = vaccineType; }
    
    public LocalDate getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(LocalDate scheduledDate) { this.scheduledDate = scheduledDate; }
    
    public LocalDate getAdministeredDate() { return administeredDate; }
    public void setAdministeredDate(LocalDate administeredDate) { 
        this.administeredDate = administeredDate;
        if (administeredDate != null) {
            this.status = VaccinationStatus.COMPLETED;
        }
    }
    
    public VaccinationStatus getStatus() { return status; }
    public void setStatus(VaccinationStatus status) { this.status = status; }
    
    public String getHealthCenterName() { return healthCenterName; }
    public void setHealthCenterName(String healthCenterName) { this.healthCenterName = healthCenterName; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    // Enums
    public enum VaccineType {
        // Birth vaccines
        BCG, HEPATITIS_B_BIRTH,
        
        // 6 weeks
        DPT1, POLIO_OPV1, HEPATITIS_B1, HIB1, ROTAVIRUS1, PCV1,
        
        // 10 weeks  
        DPT2, POLIO_OPV2, HEPATITIS_B2, HIB2, ROTAVIRUS2, PCV2,
        
        // 14 weeks
        DPT3, POLIO_OPV3, HEPATITIS_B3, HIB3, ROTAVIRUS3, PCV3,
        
        // 9-12 months
        MEASLES_RUBELLA1, JE1, VITAMIN_A1,
        
        // 16-24 months
        DPT_BOOSTER1, POLIO_OPV_BOOSTER, MEASLES_RUBELLA2, JE2, VITAMIN_A2,
        
        // 5-6 years
        DPT_BOOSTER2,
        
        // 10 years
        TETANUS_DIPHTHERIA,
        
        // 16 years
        TETANUS_DIPHTHERIA_BOOSTER,
        
        // Pregnancy vaccines
        TETANUS_TOXOID1, TETANUS_TOXOID2,
        
        // COVID-19
        COVID19_DOSE1, COVID19_DOSE2, COVID19_BOOSTER,
        
        // Seasonal
        INFLUENZA_ANNUAL
    }
    
    public enum VaccinationStatus {
        SCHEDULED, OVERDUE, COMPLETED, MISSED, CONTRAINDICATED
    }
}