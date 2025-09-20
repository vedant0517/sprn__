package com.sushrutai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SushrutAI - AI-driven public health assistant for rural and semi-urban India
 * 
 * Provides disease awareness, preventive healthcare tips, vaccination reminders,
 * and local-language health guidance across low-connectivity environments.
 * 
 * Features:
 * - Symptom checking and health guidance
 * - Vaccination tracking and reminders
 * - Seasonal and outbreak alerts
 * - Multi-language support (Hindi, Odia, tribal dialects)
 * - SMS/IVR support for low-connectivity areas
 * - DPDP Act 2023 compliant privacy protection
 */
@SpringBootApplication
@EnableScheduling
public class SushrutAIApplication {

    public static void main(String[] args) {
        SpringApplication.run(SushrutAIApplication.class, args);
        System.out.println("🏥 SushrutAI Health Assistant Started Successfully!");
        System.out.println("📱 Supporting SMS, IVR, and WhatsApp channels");
        System.out.println("🌍 Serving rural and semi-urban India");
    }
}