package com.norra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.norra.config.DataSource;
import com.norra.constants.Constants;
import com.norra.entities.UserSummary;

import java.util.List;

public interface UserSummaryRepository extends JpaRepository<UserSummary, Long>  {

    /**
     * This query returns user summary detail for a particular userId
     * @param : userId - User Id of type Long
     * @return : returns list of user config object
     */
    @Query("SELECT r FROM UserSummary r WHERE r.userId = ?1")
    UserSummary getUserSummaryByUserId(Long userId);

    /**
     * This query is used to find the UserSummary by UserId.
     * @param userId - type of long
     * @return - returns the UserSummary object.
     */
    UserSummary findByUserId(Long userId);

    /**
     * This query is used to find the user has not updated bank details.
     * @return
     */
    @Query("select userSummary from UserSummary userSummary where rideOffered >= 1 and bankDetailsApproved = false")
    List<UserSummary> findUserByRideOffered();
}
