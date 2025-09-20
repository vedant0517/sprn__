package com.sushrutai.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

/**
 * Health Dialog Controller
 * Handles multi-lingual health conversations, symptom reporting, and health guidance
 */
@RestController
@RequestMapping("/dialog")
@CrossOrigin(origins = "*")
public class DialogController {
    
    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "SushrutAI Health Assistant");
        response.put("timestamp", LocalDateTime.now());
        response.put("message", "स्वागत है! Welcome to SushrutAI - Your AI Health Assistant");
        return ResponseEntity.ok(response);
    }
    
    /**
     * Process symptom input from user (text/voice/SMS)
     */
    @PostMapping("/symptom")
    public ResponseEntity<Map<String, Object>> processSymptom(@RequestBody Map<String, Object> request) {
        String symptoms = (String) request.get("symptoms");
        String language = (String) request.getOrDefault("language", "HINDI");
        String severity = (String) request.getOrDefault("severity", "MILD");
        
        Map<String, Object> response = new HashMap<>();
        response.put("acknowledged", true);
        response.put("symptoms_received", symptoms);
        response.put("language", language);
        
        // Simple symptom analysis (placeholder for AI logic)
        String guidance = generateHealthGuidance(symptoms, language, severity);
        response.put("guidance", guidance);
        response.put("disclaimer", getDisclaimer(language));
        response.put("next_steps", getNextSteps(severity, language));
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get vaccination schedule for age group
     */
    @GetMapping("/vaccination/{ageGroup}")
    public ResponseEntity<Map<String, Object>> getVaccinationSchedule(@PathVariable String ageGroup) {
        Map<String, Object> response = new HashMap<>();
        response.put("age_group", ageGroup);
        response.put("vaccinations", getVaccinationsForAge(ageGroup));
        response.put("next_due", "Check with your nearest PHC");
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Emergency contact information
     */
    @GetMapping("/emergency")
    public ResponseEntity<Map<String, Object>> getEmergencyContacts() {
        Map<String, Object> response = new HashMap<>();
        response.put("ambulance", "108");
        response.put("women_helpline", "1091");
        response.put("child_helpline", "1098");
        response.put("poison_control", "1066");
        response.put("message", "Call 108 for medical emergency / आपातकाल के लिए 108 पर कॉल करें");
        
        return ResponseEntity.ok(response);
    }
    
    // Helper methods
    private String generateHealthGuidance(String symptoms, String language, String severity) {
        // Placeholder for AI-driven health guidance
        if (symptoms.toLowerCase().contains("fever")) {
            if ("HINDI".equals(language)) {
                return "बुखार के लिए: पानी पिएं, आराम करें। यदि 101°F से ज्यादा हो तो नजदीकी PHC जाएं।";
            }
            return "For fever: Stay hydrated, rest. If above 101°F, visit nearest PHC.";
        }
        
        if (symptoms.toLowerCase().contains("cough")) {
            if ("HINDI".equals(language)) {
                return "खांसी के लिए: गर्म पानी पिएं, भाप लें। सांस लेने में परेशानी हो तो तुरंत डॉक्टर को दिखाएं।";
            }
            return "For cough: Drink warm water, take steam. If breathing difficulty, see doctor immediately.";
        }
        
        return "Please monitor symptoms and visit PHC if worsening / लक्षणों पर नजर रखें";
    }
    
    private String getDisclaimer(String language) {
        if ("HINDI".equals(language)) {
            return "यह चिकित्सा निदान नहीं है। गंभीर लक्षणों के लिए नजदीकी PHC जाएं।";
        }
        return "This is not a medical diagnosis. Visit nearest PHC for serious symptoms.";
    }
    
    private String getNextSteps(String severity, String language) {
        if ("SEVERE".equals(severity) || "EMERGENCY".equals(severity)) {
            if ("HINDI".equals(language)) {
                return "तुरंत नजदीकी अस्पताल जाएं या 108 पर कॉल करें";
            }
            return "Visit nearest hospital immediately or call 108";
        }
        
        if ("HINDI".equals(language)) {
            return "लक्षणों पर नजर रखें, पानी पिएं, आराम करें";
        }
        return "Monitor symptoms, stay hydrated, rest";
    }
    
    private String getVaccinationsForAge(String ageGroup) {
        switch (ageGroup.toUpperCase()) {
            case "INFANT_0_1":
                return "BCG, Hepatitis B (at birth), DPT series, Polio, Rotavirus";
            case "CHILD_1_5":
                return "Measles-Rubella, Japanese Encephalitis, DPT booster";
            case "ADULT_18_30":
                return "COVID-19, Influenza (annual), Tetanus booster";
            default:
                return "Consult with ASHA worker or PHC for age-appropriate vaccines";
        }
    }
}