package com.sushrutai.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User entity with DPDP Act 2023 compliance
 * Stores minimal necessary information with proper anonymization capabilities
 */
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Anonymized identifier for tracking without personal data
    @Column(unique = true, nullable = false)
    private String anonymousId;
    
    // Phone number for SMS/IVR (encrypted at rest)
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian mobile number")
    private String phoneNumber;
    
    // Location data for regional health alerts (district level only)
    @NotBlank
    @Size(max = 100)
    private String district;
    
    @NotBlank
    @Size(max = 100)
    private String state;
    
    // Preferred language for health communication
    @Enumerated(EnumType.STRING)
    private Language preferredLanguage;
    
    // Age group instead of exact age for privacy
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;
    
    // Gender for health recommendations
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    // DPDP Act compliance fields
    @Column(nullable = false)
    private Boolean consentGiven = false;
    
    @Column(nullable = false)
    private LocalDateTime consentDate;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    // Communication preferences
    @Column(nullable = false)
    private Boolean smsEnabled = true;
    
    @Column(nullable = false)
    private Boolean ivrEnabled = true;
    
    @Column(nullable = false)
    private Boolean whatsappEnabled = false;
    
    // Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VaccinationRecord> vaccinationRecords;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SymptomReport> symptomReports;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reminder> reminders;
    
    // Constructors
    public User() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public User(String anonymousId, String phoneNumber, String district, String state, 
                Language preferredLanguage, AgeGroup ageGroup, Gender gender) {
        this();
        this.anonymousId = anonymousId;
        this.phoneNumber = phoneNumber;
        this.district = district;
        this.state = state;
        this.preferredLanguage = preferredLanguage;
        this.ageGroup = ageGroup;
        this.gender = gender;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getAnonymousId() { return anonymousId; }
    public void setAnonymousId(String anonymousId) { this.anonymousId = anonymousId; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public Language getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(Language preferredLanguage) { this.preferredLanguage = preferredLanguage; }
    
    public AgeGroup getAgeGroup() { return ageGroup; }
    public void setAgeGroup(AgeGroup ageGroup) { this.ageGroup = ageGroup; }
    
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    
    public Boolean getConsentGiven() { return consentGiven; }
    public void setConsentGiven(Boolean consentGiven) { 
        this.consentGiven = consentGiven;
        if (consentGiven) {
            this.consentDate = LocalDateTime.now();
        }
    }
    
    public LocalDateTime getConsentDate() { return consentDate; }
    public void setConsentDate(LocalDateTime consentDate) { this.consentDate = consentDate; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public Boolean getSmsEnabled() { return smsEnabled; }
    public void setSmsEnabled(Boolean smsEnabled) { this.smsEnabled = smsEnabled; }
    
    public Boolean getIvrEnabled() { return ivrEnabled; }
    public void setIvrEnabled(Boolean ivrEnabled) { this.ivrEnabled = ivrEnabled; }
    
    public Boolean getWhatsappEnabled() { return whatsappEnabled; }
    public void setWhatsappEnabled(Boolean whatsappEnabled) { this.whatsappEnabled = whatsappEnabled; }
    
    public List<VaccinationRecord> getVaccinationRecords() { return vaccinationRecords; }
    public void setVaccinationRecords(List<VaccinationRecord> vaccinationRecords) { this.vaccinationRecords = vaccinationRecords; }
    
    public List<SymptomReport> getSymptomReports() { return symptomReports; }
    public void setSymptomReports(List<SymptomReport> symptomReports) { this.symptomReports = symptomReports; }
    
    public List<Reminder> getReminders() { return reminders; }
    public void setReminders(List<Reminder> reminders) { this.reminders = reminders; }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    // Enums
    public enum Language {
        HINDI, ODIA, ENGLISH, SANTALI, HO, MUNDA
    }
    
    public enum AgeGroup {
        INFANT_0_1, CHILD_1_5, CHILD_5_12, TEEN_12_18, 
        ADULT_18_30, ADULT_30_50, ADULT_50_65, SENIOR_65_PLUS
    }
    
    public enum Gender {
        MALE, FEMALE, OTHER
    }
}