package com.sushrutai.repository;

import com.sushrutai.model.User;
import com.sushrutai.model.VaccinationRecord;
import com.sushrutai.model.VaccinationRecord.VaccinationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {
    
    /**
     * Find all vaccination records for a user ordered by scheduled date
     */
    List<VaccinationRecord> findByUserOrderByScheduledDate(User user);
    
    /**
     * Find overdue vaccinations for a user
     */
    List<VaccinationRecord> findByUserAndScheduledDateBeforeAndStatus(
        User user, LocalDate date, VaccinationStatus status);
    
    /**
     * Find upcoming vaccinations for a user
     */
    @Query("SELECT v FROM VaccinationRecord v WHERE v.user = :user AND v.scheduledDate > :date AND v.status = :status ORDER BY v.scheduledDate ASC")
    List<VaccinationRecord> findByUserAndScheduledDateAfterAndStatusOrderByScheduledDate(
        @Param("user") User user, @Param("date") LocalDate date, @Param("status") VaccinationStatus status);
    
    /**
     * Find all overdue vaccinations system-wide
     */
    List<VaccinationRecord> findByScheduledDateBeforeAndStatus(LocalDate date, VaccinationStatus status);
    
    /**
     * Count completed vaccinations for a user
     */
    @Query("SELECT COUNT(v) FROM VaccinationRecord v WHERE v.user = :user AND v.status = :status")
    long countByUserAndStatus(@Param("user") User user, @Param("status") VaccinationStatus status);
    
    /**
     * Find vaccinations by vaccine type for a user
     */
    List<VaccinationRecord> findByUserAndVaccineType(User user, VaccinationRecord.VaccineType vaccineType);
    
    /**
     * Find vaccinations due in next N days
     */
    @Query("SELECT v FROM VaccinationRecord v WHERE v.scheduledDate BETWEEN :startDate AND :endDate AND v.status = :status")
    List<VaccinationRecord> findVaccinationsDueInPeriod(
        @Param("startDate") LocalDate startDate, 
        @Param("endDate") LocalDate endDate, 
        @Param("status") VaccinationStatus status);
    
    /**
     * Get vaccination coverage statistics for a region
     */
    @Query("SELECT u.district, u.state, COUNT(v), " +
           "SUM(CASE WHEN v.status = 'COMPLETED' THEN 1 ELSE 0 END) as completed " +
           "FROM VaccinationRecord v JOIN v.user u " +
           "WHERE u.district = :district AND u.state = :state " +
           "GROUP BY u.district, u.state")
    List<Object[]> getVaccinationStatsByRegion(@Param("district") String district, @Param("state") String state);
}