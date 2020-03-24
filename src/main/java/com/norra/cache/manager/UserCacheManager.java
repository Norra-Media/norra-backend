package com.norra.cache.manager;

import com.norra.model.request.User;

public interface UserCacheManager {

    /**
     * This method is used to cache User details in Redis
     * @param user
     */
    void cacheUserDetails(User user);

    /**
     * This method is used to fetch user details from cache using userId
     * @param userId
     * @return
     */
    User getCachedUserDetails(Long userId);
}
