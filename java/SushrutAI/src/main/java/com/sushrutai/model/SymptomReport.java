package com.sushrutai.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Anonymous symptom reports for outbreak detection and health guidance
 * No personal identifiers stored - only anonymized data for analytics
 */
@Entity
@Table(name = "symptom_reports")
public class SymptomReport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    // Anonymized location data (district level only)
    @NotBlank
    @Size(max = 100)
    private String district;
    
    @NotBlank
    @Size(max = 100)
    private String state;
    
    // Reported symptoms as enum for easy analysis
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "reported_symptoms", joinColumns = @JoinColumn(name = "symptom_report_id"))
    private List<SymptomType> symptoms;
    
    // Severity assessment
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity;
    
    // Duration of symptoms
    @Enumerated(EnumType.STRING)
    private Duration duration;
    
    // Additional context in user's language
    @Size(max = 500)
    private String description;
    
    // Age group for epidemiological analysis
    @Enumerated(EnumType.STRING)
    private User.AgeGroup ageGroup;
    
    // Gender for health pattern analysis
    @Enumerated(EnumType.STRING)
    private User.Gender gender;
    
    // AI-generated health guidance provided
    @Column(columnDefinition = "TEXT")
    private String guidanceProvided;
    
    // Follow-up recommendation given
    @Enumerated(EnumType.STRING)
    private FollowUpAction recommendedAction;
    
    @Column(nullable = false)
    private LocalDateTime reportedAt;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    // Constructors
    public SymptomReport() {
        this.reportedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
    
    public SymptomReport(User user, List<SymptomType> symptoms, Severity severity) {
        this();
        this.user = user;
        this.symptoms = symptoms;
        this.severity = severity;
        this.district = user.getDistrict();
        this.state = user.getState();
        this.ageGroup = user.getAgeGroup();
        this.gender = user.getGender();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public List<SymptomType> getSymptoms() { return symptoms; }
    public void setSymptoms(List<SymptomType> symptoms) { this.symptoms = symptoms; }
    
    public Severity getSeverity() { return severity; }
    public void setSeverity(Severity severity) { this.severity = severity; }
    
    public Duration getDuration() { return duration; }
    public void setDuration(Duration duration) { this.duration = duration; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public User.AgeGroup getAgeGroup() { return ageGroup; }
    public void setAgeGroup(User.AgeGroup ageGroup) { this.ageGroup = ageGroup; }
    
    public User.Gender getGender() { return gender; }
    public void setGender(User.Gender gender) { this.gender = gender; }
    
    public String getGuidanceProvided() { return guidanceProvided; }
    public void setGuidanceProvided(String guidanceProvided) { this.guidanceProvided = guidanceProvided; }
    
    public FollowUpAction getRecommendedAction() { return recommendedAction; }
    public void setRecommendedAction(FollowUpAction recommendedAction) { this.recommendedAction = recommendedAction; }
    
    public LocalDateTime getReportedAt() { return reportedAt; }
    public void setReportedAt(LocalDateTime reportedAt) { this.reportedAt = reportedAt; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    // Enums
    public enum SymptomType {
        // Common symptoms
        FEVER, COUGH, COLD, HEADACHE, BODY_ACHE, FATIGUE, WEAKNESS,
        
        // Respiratory
        DIFFICULTY_BREATHING, CHEST_PAIN, SORE_THROAT, RUNNY_NOSE,
        
        // Gastrointestinal  
        NAUSEA, VOMITING, DIARRHEA, STOMACH_PAIN, LOSS_OF_APPETITE,
        
        // Skin related
        RASH, ITCHING, SWELLING, REDNESS,
        
        // Neurological
        DIZZINESS, CONFUSION, LOSS_OF_CONSCIOUSNESS,
        
        // Pregnancy related
        BLEEDING_PREGNANCY, SEVERE_MORNING_SICKNESS, REDUCED_FETAL_MOVEMENT,
        
        // Child specific
        CRYING_EXCESSIVE, REFUSING_FEED, LETHARGY_CHILD,
        
        // Other
        HIGH_BLOOD_PRESSURE, BLOOD_SUGAR_HIGH, WOUND_NOT_HEALING, UNUSUAL_DISCHARGE
    }
    
    public enum Severity {
        MILD, MODERATE, SEVERE, EMERGENCY
    }
    
    public enum Duration {
        LESS_THAN_1_DAY, ONE_TO_THREE_DAYS, THREE_TO_SEVEN_DAYS, 
        ONE_TO_TWO_WEEKS, MORE_THAN_TWO_WEEKS
    }
    
    public enum FollowUpAction {
        HOME_CARE, MONITOR_AT_HOME, VISIT_ASHA, VISIT_PHC, 
        VISIT_CHC, EMERGENCY_CARE, CALL_AMBULANCE
    }
}