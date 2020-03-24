package com.norra.service;

import com.norra.entities.UserSummary;
import com.norra.enums.UserType;
import com.norra.model.request.User;
import com.norra.model.response.ResponseModel;

public interface UserSummaryService {

    /**
     * Gets the user summary by user id.
     *
     * @param userId the user id
     * @return the user summary by user id
     */
    UserSummary getUserSummaryByUserId(Long userId);


    /**
     * This method is used to add rating and reviews for particular user based on guest or host
     * @param userId used id of type Long
     * @param userType user type of type String
     * @param rating rating of type Float
     * @return returns response entity
     */
    ResponseModel updateUserSummary(Long userId, UserType userType, Float rating, int rideTaken, int rideOffered, Long tripId);



    /**
     * This method is used to store the user summary to redis based on userId.
     * @return returns user object
     */
    User userSummary(User user);

    /**
     * This method is used to return the guest of host details based on user type
     * @param rideId ride id of type Long
     * @param userType user type of
     * @return returns response model
     */

    /**
     * This method is used to return the user summary data based on user id
     *
     * @param userId user id of type Long
     * @return returns response model
     */
    ResponseModel getUserSummary(Long userId);



    /**
     * This method is used to fetch user details from cache
     * @param userId
     * @return
     */
    ResponseModel getUserDetailsFromCache(Long userId);

}
