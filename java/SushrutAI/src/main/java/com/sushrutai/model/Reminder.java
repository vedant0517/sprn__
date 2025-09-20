package com.sushrutai.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * Reminder entity for health-related notifications
 * Supports vaccination reminders, check-ups, and medication schedules
 */
@Entity
@Table(name = "reminders")
public class Reminder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReminderType type;
    
    @NotBlank
    @Size(max = 200)
    private String title;
    
    @Size(max = 500)
    private String description;
    
    @Column(nullable = false)
    private LocalDateTime scheduledTime;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReminderStatus status;
    
    // Communication preferences for this reminder
    @Column(nullable = false)
    private Boolean sendSms = true;
    
    @Column(nullable = false)
    private Boolean sendIvr = false;
    
    @Column(nullable = false)
    private Boolean sendWhatsapp = false;
    
    // Language for the reminder message
    @Enumerated(EnumType.STRING)
    private User.Language language;
    
    // Recurrence settings
    @Enumerated(EnumType.STRING)
    private Recurrence recurrence;
    
    // Tracking
    private LocalDateTime sentAt;
    private LocalDateTime acknowledgedAt;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    // Constructors
    public Reminder() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = ReminderStatus.PENDING;
    }
    
    public Reminder(User user, ReminderType type, String title, LocalDateTime scheduledTime) {
        this();
        this.user = user;
        this.type = type;
        this.title = title;
        this.scheduledTime = scheduledTime;
        this.language = user.getPreferredLanguage();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public ReminderType getType() { return type; }
    public void setType(ReminderType type) { this.type = type; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }
    
    public ReminderStatus getStatus() { return status; }
    public void setStatus(ReminderStatus status) { this.status = status; }
    
    public Boolean getSendSms() { return sendSms; }
    public void setSendSms(Boolean sendSms) { this.sendSms = sendSms; }
    
    public Boolean getSendIvr() { return sendIvr; }
    public void setSendIvr(Boolean sendIvr) { this.sendIvr = sendIvr; }
    
    public Boolean getSendWhatsapp() { return sendWhatsapp; }
    public void setSendWhatsapp(Boolean sendWhatsapp) { this.sendWhatsapp = sendWhatsapp; }
    
    public User.Language getLanguage() { return language; }
    public void setLanguage(User.Language language) { this.language = language; }
    
    public Recurrence getRecurrence() { return recurrence; }
    public void setRecurrence(Recurrence recurrence) { this.recurrence = recurrence; }
    
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
    
    public LocalDateTime getAcknowledgedAt() { return acknowledgedAt; }
    public void setAcknowledgedAt(LocalDateTime acknowledgedAt) { this.acknowledgedAt = acknowledgedAt; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    // Mark reminder as sent
    public void markAsSent() {
        this.status = ReminderStatus.SENT;
        this.sentAt = LocalDateTime.now();
    }
    
    // Mark reminder as acknowledged by user
    public void markAsAcknowledged() {
        this.status = ReminderStatus.ACKNOWLEDGED;
        this.acknowledgedAt = LocalDateTime.now();
    }
    
    // Enums
    public enum ReminderType {
        VACCINATION, ANC_CHECKUP, NUTRITION_ADVICE, HYGIENE_PRACTICE,
        MEDICATION, FOLLOW_UP_VISIT, HEALTH_SCREENING, SEASONAL_ALERT
    }
    
    public enum ReminderStatus {
        PENDING, SENT, ACKNOWLEDGED, EXPIRED, CANCELLED
    }
    
    public enum Recurrence {
        NONE, DAILY, WEEKLY, MONTHLY, YEARLY
    }
}