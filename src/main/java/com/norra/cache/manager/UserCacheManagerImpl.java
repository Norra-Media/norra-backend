package com.norra.cache.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.norra.cache.util.CacheUtility;
import com.norra.constants.CacheConstants;
import com.norra.constants.Constants;
import com.norra.enums.AppConfigKey;
import com.norra.enums.AppConfigType;
import com.norra.model.request.User;
import com.norra.service.AppConfigService;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class UserCacheManagerImpl implements UserCacheManager {

    private CacheUtility<User> cacheUtilityUser;
    private final static String REDIS_USER_KEY = "USER_";
    private AppConfigService appConfigService;

    @Autowired
    public UserCacheManagerImpl(CacheUtility<User> cacheUtilityUser, AppConfigService appConfigService){
        this.cacheUtilityUser = cacheUtilityUser;
        this.appConfigService = appConfigService;
    }

    /**
     * This method is used to cache User details in Redis
     *
     * @param user
     */
    @Override
    public void cacheUserDetails(User user) {
        long userCacheExpiryTime = 0;
        log.debug("Save user with id = {} in cache",user.getId());
        HashMap<AppConfigKey, String> rideConfigs = appConfigService
                .getAppConfigMapByConfigType(AppConfigType.RIDE_CONFIGS);
        userCacheExpiryTime = Long.parseLong(rideConfigs.get(AppConfigKey.USER_CACHE_TTL));
        if(userCacheExpiryTime == 0) {
            userCacheExpiryTime = CacheConstants.USER_CACHE_EXPIRY_TIME;
        }
        this.cacheUtilityUser.putInMap(REDIS_USER_KEY+user.getId(),user.getId(),user);
        this.cacheUtilityUser.setExpire(REDIS_USER_KEY+user.getId(), userCacheExpiryTime, TimeUnit.MINUTES);
        log.debug("User data saved in cache");
    }

    /**
     * This method is used to fetch user details from cache using userId
     *
     * @param userId
     * @return
     */
    @Override
    public User getCachedUserDetails(Long userId) {
        log.debug("Fetch user data with {} from cache",userId);
        User user = new User();
        String redisKey = REDIS_USER_KEY+userId;
        Object userObj = this.cacheUtilityUser.getFromMap(redisKey,userId);
        if(userObj != null){
            user = (User)userObj;
        }
        log.debug("User fetched from cache = "+user.toString());
        return user;
    }
}
