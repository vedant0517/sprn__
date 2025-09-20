package com.sushrutai.repository;

import com.sushrutai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find user by anonymous ID
     */
    Optional<User> findByAnonymousId(String anonymousId);
    
    /**
     * Find user by phone number
     */
    Optional<User> findByPhoneNumber(String phoneNumber);
    
    /**
     * Find users by district and state for regional health statistics
     */
    List<User> findByDistrictAndState(String district, String state);
    
    /**
     * Find users by age group for vaccination campaigns
     */
    @Query("SELECT u FROM User u WHERE u.ageGroup = :ageGroup")
    List<User> findByAgeGroup(@Param("ageGroup") User.AgeGroup ageGroup);
    
    /**
     * Find users who have given consent for health data usage
     */
    @Query("SELECT u FROM User u WHERE u.consentGiven = true")
    List<User> findUsersWithDataConsent();
    
    /**
     * Find users in a specific district for targeted health campaigns
     */
    List<User> findByDistrict(String district);
    
    /**
     * Find users by state
     */
    List<User> findByState(String state);
    
    /**
     * Count users by district for population health metrics
     */
    @Query("SELECT u.district, COUNT(u) FROM User u WHERE u.state = :state GROUP BY u.district")
    List<Object[]> countUsersByDistrictInState(@Param("state") String state);
    
    /**
     * Find users with specific preferred language for localized communications
     */
    List<User> findByPreferredLanguage(User.Language preferredLanguage);
    
    /**
     * Check if anonymous ID exists
     */
    boolean existsByAnonymousId(String anonymousId);
    
    /**
     * Check if phone number exists
     */
    boolean existsByPhoneNumber(String phoneNumber);
}