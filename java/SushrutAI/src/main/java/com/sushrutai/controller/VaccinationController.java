package com.sushrutai.controller;

import com.sushrutai.model.User;
import com.sushrutai.model.VaccinationRecord;
import com.sushrutai.service.VaccinationService;
import com.sushrutai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/vaccination")
@CrossOrigin(origins = "*")
public class VaccinationController {

    @Autowired
    private VaccinationService vaccinationService;
    
    @Autowired
    private UserRepository userRepository;

    /**
     * Create vaccination schedule for a new user
     * POST /api/vaccination/schedule
     */
    @PostMapping("/schedule")
    public ResponseEntity<Map<String, Object>> createVaccinationSchedule(
            @RequestParam @NotBlank String anonymousId,
            @RequestParam @NotNull String birthDate) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            LocalDate birth = LocalDate.parse(birthDate);
            List<VaccinationRecord> schedule = vaccinationService.createVaccinationSchedule(anonymousId, birth);
            
            response.put("success", true);
            response.put("message", "Vaccination schedule created successfully");
            response.put("scheduleCount", schedule.size());
            response.put("schedule", schedule.stream().map(this::convertToResponse).toList());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error creating vaccination schedule: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get vaccination schedule for a user
     * GET /api/vaccination/schedule/{anonymousId}
     */
    @GetMapping("/schedule/{anonymousId}")
    public ResponseEntity<Map<String, Object>> getVaccinationSchedule(@PathVariable String anonymousId) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<VaccinationRecord> schedule = vaccinationService.getVaccinationSchedule(anonymousId);
            double coverage = vaccinationService.getVaccinationCoverage(anonymousId);
            Optional<VaccinationRecord> nextVaccination = vaccinationService.getNextVaccination(anonymousId);
            
            response.put("success", true);
            response.put("schedule", schedule.stream().map(this::convertToResponse).toList());
            response.put("coverage", coverage);
            response.put("totalVaccines", schedule.size());
            response.put("nextVaccination", nextVaccination.map(this::convertToResponse).orElse(null));
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error fetching vaccination schedule: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get overdue vaccinations for a user
     * GET /api/vaccination/overdue/{anonymousId}
     */
    @GetMapping("/overdue/{anonymousId}")
    public ResponseEntity<Map<String, Object>> getOverdueVaccinations(@PathVariable String anonymousId) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<VaccinationRecord> overdueVaccinations = vaccinationService.getOverdueVaccinations(anonymousId);
            
            response.put("success", true);
            response.put("overdueCount", overdueVaccinations.size());
            response.put("overdueVaccinations", overdueVaccinations.stream().map(this::convertToResponse).toList());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error fetching overdue vaccinations: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Mark vaccination as completed
     * POST /api/vaccination/complete/{vaccinationId}
     */
    @PostMapping("/complete/{vaccinationId}")
    public ResponseEntity<Map<String, Object>> markVaccinationCompleted(
            @PathVariable Long vaccinationId,
            @RequestParam String administeredDate,
            @RequestParam(required = false) String healthCenterName,
            @RequestParam(required = false) String notes) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            LocalDate adminDate = LocalDate.parse(administeredDate);
            VaccinationRecord updatedRecord = vaccinationService.markVaccinationCompleted(
                vaccinationId, adminDate, healthCenterName, notes);
            
            response.put("success", true);
            response.put("message", "Vaccination marked as completed");
            response.put("vaccination", convertToResponse(updatedRecord));
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error marking vaccination as completed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get vaccination statistics for a region
     * GET /api/vaccination/stats
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getVaccinationStatistics(
            @RequestParam String district,
            @RequestParam String state) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> stats = vaccinationService.getVaccinationStatistics(district, state);
            
            response.put("success", true);
            response.put("statistics", stats);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error fetching vaccination statistics: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get vaccination reminders (due in next 7 days)
     * GET /api/vaccination/reminders/{anonymousId}
     */
    @GetMapping("/reminders/{anonymousId}")
    public ResponseEntity<Map<String, Object>> getVaccinationReminders(@PathVariable String anonymousId) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            Optional<User> userOpt = userRepository.findByAnonymousId(anonymousId);
            if (userOpt.isEmpty()) {
                response.put("success", false);
                response.put("message", "User not found");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Get next vaccination due
            Optional<VaccinationRecord> nextVaccination = vaccinationService.getNextVaccination(anonymousId);
            List<VaccinationRecord> overdueVaccinations = vaccinationService.getOverdueVaccinations(anonymousId);
            
            Map<String, Object> reminders = new HashMap<>();
            reminders.put("nextVaccination", nextVaccination.map(this::convertToResponse).orElse(null));
            reminders.put("overdueVaccinations", overdueVaccinations.stream().map(this::convertToResponse).toList());
            reminders.put("overdueCount", overdueVaccinations.size());
            
            // Check if next vaccination is due soon (within 7 days)
            boolean urgentReminder = nextVaccination.map(vaccine -> 
                vaccine.getScheduledDate().isBefore(LocalDate.now().plusDays(7))
            ).orElse(false);
            
            reminders.put("urgentReminder", urgentReminder || !overdueVaccinations.isEmpty());
            
            response.put("success", true);
            response.put("reminders", reminders);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error fetching vaccination reminders: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get UIP (Universal Immunization Programme) information
     * GET /api/vaccination/uip-info
     */
    @GetMapping("/uip-info")
    public ResponseEntity<Map<String, Object>> getUIPInformation() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> uipInfo = new HashMap<>();
            
            // Birth vaccines
            uipInfo.put("birth", Arrays.asList(
                createVaccineInfo("BCG", "Bacillus Calmette-Guérin", "At birth", "Prevents tuberculosis"),
                createVaccineInfo("HEPATITIS_B_BIRTH", "Hepatitis B", "At birth", "Prevents hepatitis B infection")
            ));
            
            // 6 weeks vaccines
            uipInfo.put("6_weeks", Arrays.asList(
                createVaccineInfo("DPT1", "Diphtheria, Pertussis, Tetanus - 1st dose", "6 weeks", "Prevents diphtheria, whooping cough, tetanus"),
                createVaccineInfo("POLIO_OPV1", "Oral Polio Vaccine - 1st dose", "6 weeks", "Prevents poliomyelitis"),
                createVaccineInfo("HEPATITIS_B1", "Hepatitis B - 1st dose", "6 weeks", "Prevents hepatitis B infection"),
                createVaccineInfo("HIB1", "Haemophilus influenzae type b - 1st dose", "6 weeks", "Prevents pneumonia and meningitis"),
                createVaccineInfo("ROTAVIRUS1", "Rotavirus - 1st dose", "6 weeks", "Prevents severe diarrhea"),
                createVaccineInfo("PCV1", "Pneumococcal Conjugate - 1st dose", "6 weeks", "Prevents pneumonia")
            ));
            
            // 10 weeks vaccines
            uipInfo.put("10_weeks", Arrays.asList(
                createVaccineInfo("DPT2", "DPT - 2nd dose", "10 weeks", "Booster for DPT protection"),
                createVaccineInfo("POLIO_OPV2", "OPV - 2nd dose", "10 weeks", "Booster for polio protection"),
                createVaccineInfo("HEPATITIS_B2", "Hepatitis B - 2nd dose", "10 weeks", "Booster for hepatitis B protection"),
                createVaccineInfo("HIB2", "Hib - 2nd dose", "10 weeks", "Booster for Hib protection"),
                createVaccineInfo("ROTAVIRUS2", "Rotavirus - 2nd dose", "10 weeks", "Booster for rotavirus protection"),
                createVaccineInfo("PCV2", "PCV - 2nd dose", "10 weeks", "Booster for pneumococcal protection")
            ));
            
            // 14 weeks vaccines
            uipInfo.put("14_weeks", Arrays.asList(
                createVaccineInfo("DPT3", "DPT - 3rd dose", "14 weeks", "Final primary DPT dose"),
                createVaccineInfo("POLIO_OPV3", "OPV - 3rd dose", "14 weeks", "Final primary polio dose"),
                createVaccineInfo("HEPATITIS_B3", "Hepatitis B - 3rd dose", "14 weeks", "Final primary hepatitis B dose"),
                createVaccineInfo("HIB3", "Hib - 3rd dose", "14 weeks", "Final primary Hib dose"),
                createVaccineInfo("ROTAVIRUS3", "Rotavirus - 3rd dose", "14 weeks", "Final rotavirus dose"),
                createVaccineInfo("PCV3", "PCV - 3rd dose", "14 weeks", "Final primary PCV dose")
            ));
            
            // 9-12 months vaccines
            uipInfo.put("9_12_months", Arrays.asList(
                createVaccineInfo("MEASLES_RUBELLA1", "Measles-Rubella - 1st dose", "9-12 months", "Prevents measles and rubella"),
                createVaccineInfo("JE1", "Japanese Encephalitis - 1st dose", "9-12 months", "Prevents Japanese encephalitis"),
                createVaccineInfo("VITAMIN_A1", "Vitamin A - 1st dose", "9-12 months", "Prevents vitamin A deficiency")
            ));
            
            response.put("success", true);
            response.put("uipSchedule", uipInfo);
            response.put("message", "Universal Immunization Programme schedule as per Government of India guidelines");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error fetching UIP information: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Convert VaccinationRecord to response format
     */
    private Map<String, Object> convertToResponse(VaccinationRecord record) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", record.getId());
        response.put("vaccineType", record.getVaccineType().toString());
        response.put("scheduledDate", record.getScheduledDate().toString());
        response.put("administeredDate", record.getAdministeredDate() != null ? record.getAdministeredDate().toString() : null);
        response.put("status", record.getStatus().toString());
        response.put("healthCenterName", record.getHealthCenterName());
        response.put("notes", record.getNotes());
        response.put("createdAt", record.getCreatedAt().toString());
        response.put("updatedAt", record.getUpdatedAt().toString());
        
        // Calculate if overdue
        if (record.getStatus() == VaccinationRecord.VaccinationStatus.SCHEDULED) {
            boolean isOverdue = record.getScheduledDate().isBefore(LocalDate.now());
            response.put("isOverdue", isOverdue);
            
            if (!isOverdue) {
                response.put("daysUntilDue", java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), record.getScheduledDate()));
            } else {
                response.put("daysOverdue", java.time.temporal.ChronoUnit.DAYS.between(record.getScheduledDate(), LocalDate.now()));
            }
        }
        
        return response;
    }

    /**
     * Create vaccine information for UIP guide
     */
    private Map<String, Object> createVaccineInfo(String code, String name, String timing, String purpose) {
        Map<String, Object> info = new HashMap<>();
        info.put("code", code);
        info.put("name", name);
        info.put("timing", timing);
        info.put("purpose", purpose);
        return info;
    }
}