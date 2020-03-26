package com.norra.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.norra.cache.manager.UserCacheManager;
import com.norra.model.request.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisUtil {

    public static final String USERS = "USERS";

    private UserCacheManager userCacheManager;

    @Autowired
    public RedisUtil(
                    UserCacheManager userCacheManager) {
        this.userCacheManager = userCacheManager;
    }

    /**
     * save user data to cache(redis) which is coming in create ride.
     *
     * @param user the user
     * @return the user
     */
    public User saveUserDataInCache(User user) {
        if (user == null || user.getId() == null) {
            return new User();
        }

        try {
            this.userCacheManager.cacheUserDetails(user);
        } catch (Exception e) {
            log.error("Exception while saving user {} in cache : ", user, e);
        }

        return user;
    }

    /**
     * Gets the user data from cache(redis) based on userid.. if not available in
     * redis we have to call bounce api
     *
     * @param userId the user id
     * @return the user data from redis
     */
	/*
	 * public User getUserDataById(Long userId) { return
	 * getUserDataFromCache(userId); //return fetchUserFromBounceAPI(userId); }
	 */

    /**
     * Gets the user data from cache(redis) based on userid.. if not available in
     * redis we have to call bounce api
     *
     * @param userId - type of Long, the user id
     * @return - the user data from redis
     */
	/*
	 * public User getUserDataFromCache(Long userId) { User user = new User(); if
	 * (userId == null) { return user; } try { User cachedUser =
	 * this.userCacheManager.getCachedUserDetails(userId); if (cachedUser.getId() !=
	 * null) { return cachedUser; } log.debug("User not found in cache");
	 * saveUserDataInCache(user); } catch (Exception e) {
	 * log.error("Error occurred while trying to fetch user : ", e); } return user;
	 * }
	 */


}
