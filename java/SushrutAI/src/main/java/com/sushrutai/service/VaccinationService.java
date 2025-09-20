package com.sushrutai.service;

import com.sushrutai.model.User;
import com.sushrutai.model.VaccinationRecord;
import com.sushrutai.model.VaccinationRecord.VaccineType;
import com.sushrutai.model.VaccinationRecord.VaccinationStatus;
import com.sushrutai.repository.VaccinationRecordRepository;
import com.sushrutai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class VaccinationService {

    @Autowired
    private VaccinationRecordRepository vaccinationRepository;
    
    @Autowired
    private UserRepository userRepository;

    /**
     * Universal Immunization Programme (UIP) Schedule for India
     * Age-based vaccination schedule according to Government of India guidelines
     */
    private static final Map<VaccineType, Integer> VACCINATION_SCHEDULE_DAYS = Map.ofEntries(
        // Birth vaccines
        Map.entry(VaccineType.BCG, 0),
        Map.entry(VaccineType.HEPATITIS_B_BIRTH, 0),
        
        // 6 weeks (42 days)
        Map.entry(VaccineType.DPT1, 42),
        Map.entry(VaccineType.POLIO_OPV1, 42),
        Map.entry(VaccineType.HEPATITIS_B1, 42),
        Map.entry(VaccineType.HIB1, 42),
        Map.entry(VaccineType.ROTAVIRUS1, 42),
        Map.entry(VaccineType.PCV1, 42),
        
        // 10 weeks (70 days)
        Map.entry(VaccineType.DPT2, 70),
        Map.entry(VaccineType.POLIO_OPV2, 70),
        Map.entry(VaccineType.HEPATITIS_B2, 70),
        Map.entry(VaccineType.HIB2, 70),
        Map.entry(VaccineType.ROTAVIRUS2, 70),
        Map.entry(VaccineType.PCV2, 70),
        
        // 14 weeks (98 days)
        Map.entry(VaccineType.DPT3, 98),
        Map.entry(VaccineType.POLIO_OPV3, 98),
        Map.entry(VaccineType.HEPATITIS_B3, 98),
        Map.entry(VaccineType.HIB3, 98),
        Map.entry(VaccineType.ROTAVIRUS3, 98),
        Map.entry(VaccineType.PCV3, 98),
        
        // 9-12 months (270 days average)
        Map.entry(VaccineType.MEASLES_RUBELLA1, 270),
        Map.entry(VaccineType.JE1, 270),
        Map.entry(VaccineType.VITAMIN_A1, 270),
        
        // 16-24 months (540 days)
        Map.entry(VaccineType.DPT_BOOSTER1, 540),
        Map.entry(VaccineType.POLIO_OPV_BOOSTER, 540),
        Map.entry(VaccineType.MEASLES_RUBELLA2, 540),
        Map.entry(VaccineType.JE2, 540),
        Map.entry(VaccineType.VITAMIN_A2, 540),
        
        // 5-6 years (1825 days)
        Map.entry(VaccineType.DPT_BOOSTER2, 1825),
        
        // 10 years (3650 days)
        Map.entry(VaccineType.TETANUS_DIPHTHERIA, 3650),
        
        // 16 years (5840 days)
        Map.entry(VaccineType.TETANUS_DIPHTHERIA_BOOSTER, 5840),
        
        // Pregnancy vaccines (to be scheduled separately)
        Map.entry(VaccineType.TETANUS_TOXOID1, -1), // Pregnancy dependent
        Map.entry(VaccineType.TETANUS_TOXOID2, -1)  // Pregnancy dependent
    );

    /**
     * Create vaccination schedule for a new user based on birth date
     */
    public List<VaccinationRecord> createVaccinationSchedule(String anonymousId, LocalDate birthDate) {
        Optional<User> userOpt = userRepository.findByAnonymousId(anonymousId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found: " + anonymousId);
        }
        
        User user = userOpt.get();
        List<VaccinationRecord> schedule = new ArrayList<>();
        
        for (Map.Entry<VaccineType, Integer> entry : VACCINATION_SCHEDULE_DAYS.entrySet()) {
            VaccineType vaccine = entry.getKey();
            Integer daysFromBirth = entry.getValue();
            
            // Skip pregnancy-specific vaccines for now
            if (daysFromBirth == -1) continue;
            
            LocalDate scheduledDate = birthDate.plusDays(daysFromBirth);
            
            // Only create future or recent schedules (not for adults getting child vaccines)
            if (scheduledDate.isAfter(LocalDate.now().minusYears(1))) {
                VaccinationRecord record = new VaccinationRecord();
                record.setUser(user);
                record.setVaccineType(vaccine);
                record.setScheduledDate(scheduledDate);
                record.setStatus(VaccinationStatus.SCHEDULED);
                record.setCreatedAt(LocalDateTime.now());
                record.setUpdatedAt(LocalDateTime.now());
                
                schedule.add(record);
            }
        }
        
        return vaccinationRepository.saveAll(schedule);
    }

    /**
     * Get vaccination schedule for a user
     */
    public List<VaccinationRecord> getVaccinationSchedule(String anonymousId) {
        Optional<User> userOpt = userRepository.findByAnonymousId(anonymousId);
        if (userOpt.isEmpty()) {
            return Collections.emptyList();
        }
        
        return vaccinationRepository.findByUserOrderByScheduledDate(userOpt.get());
    }

    /**
     * Get overdue vaccinations for a user
     */
    public List<VaccinationRecord> getOverdueVaccinations(String anonymousId) {
        Optional<User> userOpt = userRepository.findByAnonymousId(anonymousId);
        if (userOpt.isEmpty()) {
            return Collections.emptyList();
        }
        
        LocalDate today = LocalDate.now();
        return vaccinationRepository.findByUserAndScheduledDateBeforeAndStatus(
            userOpt.get(), today, VaccinationStatus.SCHEDULED
        );
    }

    /**
     * Mark vaccination as completed
     */
    public VaccinationRecord markVaccinationCompleted(Long vaccinationId, LocalDate administeredDate, 
                                                      String healthCenterName, String notes) {
        Optional<VaccinationRecord> recordOpt = vaccinationRepository.findById(vaccinationId);
        if (recordOpt.isEmpty()) {
            throw new RuntimeException("Vaccination record not found: " + vaccinationId);
        }
        
        VaccinationRecord record = recordOpt.get();
        record.setStatus(VaccinationStatus.COMPLETED);
        record.setAdministeredDate(administeredDate);
        record.setHealthCenterName(healthCenterName);
        record.setNotes(notes);
        record.setUpdatedAt(LocalDateTime.now());
        
        return vaccinationRepository.save(record);
    }

    /**
     * Get vaccination coverage for a user (percentage of completed vaccines)
     */
    public double getVaccinationCoverage(String anonymousId) {
        List<VaccinationRecord> schedule = getVaccinationSchedule(anonymousId);
        if (schedule.isEmpty()) {
            return 0.0;
        }
        
        long completedCount = schedule.stream()
            .filter(record -> record.getStatus() == VaccinationStatus.COMPLETED)
            .count();
            
        return (double) completedCount / schedule.size() * 100.0;
    }

    /**
     * Get next upcoming vaccination for a user
     */
    public Optional<VaccinationRecord> getNextVaccination(String anonymousId) {
        Optional<User> userOpt = userRepository.findByAnonymousId(anonymousId);
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }
        
        LocalDate today = LocalDate.now();
        List<VaccinationRecord> upcoming = vaccinationRepository
            .findByUserAndScheduledDateAfterAndStatusOrderByScheduledDate(
                userOpt.get(), today, VaccinationStatus.SCHEDULED
            );
            
        return upcoming.isEmpty() ? Optional.empty() : Optional.of(upcoming.get(0));
    }

    /**
     * Get vaccination statistics for health monitoring
     */
    public Map<String, Object> getVaccinationStatistics(String district, String state) {
        Map<String, Object> stats = new HashMap<>();
        
        // Get all users in the area (if location data available)
        List<User> users = userRepository.findByDistrictAndState(district, state);
        
        if (users.isEmpty()) {
            stats.put("totalUsers", 0);
            stats.put("averageCoverage", 0.0);
            stats.put("overdueCount", 0);
            return stats;
        }
        
        List<Double> coverageRates = users.stream()
            .map(user -> getVaccinationCoverage(user.getAnonymousId()))
            .collect(Collectors.toList());
            
        double averageCoverage = coverageRates.stream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0.0);
            
        long totalOverdue = users.stream()
            .mapToLong(user -> getOverdueVaccinations(user.getAnonymousId()).size())
            .sum();
        
        stats.put("totalUsers", users.size());
        stats.put("averageCoverage", Math.round(averageCoverage * 100.0) / 100.0);
        stats.put("overdueCount", totalOverdue);
        stats.put("coverageRates", coverageRates);
        
        return stats;
    }

    /**
     * Auto-update overdue vaccinations
     */
    @Transactional
    public void updateOverdueVaccinations() {
        LocalDate today = LocalDate.now();
        List<VaccinationRecord> overdueRecords = vaccinationRepository
            .findByScheduledDateBeforeAndStatus(today, VaccinationStatus.SCHEDULED);
            
        for (VaccinationRecord record : overdueRecords) {
            record.setStatus(VaccinationStatus.OVERDUE);
            record.setUpdatedAt(LocalDateTime.now());
        }
        
        vaccinationRepository.saveAll(overdueRecords);
    }
}