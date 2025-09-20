package com.sushrutai.service;

import com.sushrutai.model.Reminder;
import com.sushrutai.model.VaccinationRecord;
import com.sushrutai.model.User;
import com.sushrutai.repository.ReminderRepository;
import com.sushrutai.repository.VaccinationRecordRepository;
import com.sushrutai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReminderService {

    @Autowired
    private VaccinationRecordRepository vaccinationRepository;

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private UserRepository userRepository;

    // Run daily at 02:00 AM to enqueue reminders for vaccinations due in next 7 days
    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional
    public void createUpcomingVaccinationReminders() {
        LocalDate today = LocalDate.now();
        LocalDate end = today.plusDays(7);
        
        List<VaccinationRecord> dueSoon = vaccinationRepository.findVaccinationsDueInPeriod(today, end, VaccinationRecord.VaccinationStatus.SCHEDULED);
        List<Reminder> toSave = new ArrayList<>();

        for (VaccinationRecord record : dueSoon) {
            User user = record.getUser();
            if (user == null) continue;
            
            // Skip if user hasn't given consent
            if (!Boolean.TRUE.equals(user.getConsentGiven())) continue;
            
            Reminder r = new Reminder();
            r.setUser(user);
            r.setType(Reminder.ReminderType.VACCINATION);
            r.setTitle("Upcoming vaccination: " + record.getVaccineType().name());
            r.setDescription("Your child has an upcoming vaccination scheduled on " + record.getScheduledDate().toString());
            r.setScheduledTime(LocalDateTime.of(record.getScheduledDate(), java.time.LocalTime.of(9, 0))); // 9 AM
            r.setLanguage(user.getPreferredLanguage());
            r.setSendSms(user.getSmsEnabled());
            r.setSendIvr(user.getIvrEnabled());
            r.setSendWhatsapp(user.getWhatsappEnabled());
            r.setCreatedAt(LocalDateTime.now());
            r.setUpdatedAt(LocalDateTime.now());
            
            toSave.add(r);
        }
        
        if (!toSave.isEmpty()) {
            reminderRepository.saveAll(toSave);
        }
    }
}