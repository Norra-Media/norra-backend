package com.norra.cache.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheUtility<T> {
    @Autowired
    private RedisTemplate<String,T> redisTemplate;

    /**
     * This method is used to store an object in the redis map
     * @param redisKey - A pre defined key which will be an unique identifier in Redis
     * @param key - Unique identifier for the object to be stored
     * @param data
     */
    public void putInMap(String redisKey,Object key,Object data) {
        redisTemplate.opsForHash().put(redisKey,key,data);
    }

    /**
     * This method is used to fetch and return an object based on RedisKey and key from Redis
     * @param redisKey - A pre defined key which will be an unique identifier in Redis
     * @param key - Unique identifier for the object to be stored
     * @return
     */
    public Object getFromMap(String redisKey,Object key) {
        return redisTemplate.opsForHash().get(redisKey, key);
    }

    /**
     * This method is used to set to expiration duration corresponding to
     * @param key - Unique identifier for the object to be stored
     * @param timeout - Duration after which a given key will expire
     * @param unit - Unit of time
     */
    public void setExpire(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    /**
     * This method is used to add and group data in set
     * @param setName - A predefined redis set which will store keys corresponding to this set
     * @param data - Data to be stored
     * @param score - Redis set score
     */
    public void addToSet(String setName, T data, long score){
        redisTemplate.opsForZSet().add(setName, data, score);
    }

    /**
     * This method is used to return data from set based on passed score
     * @param setName - A predefined redis set from which data to be returned will be fetched using score
     * @param scoreToFetch - Score for which data needs to be fetched
     * @return
     */
    public Set<T> getFromSetBasedOnScore(String setName, long scoreToFetch) {
        Set<ZSetOperations.TypedTuple<T>> fetchedSet = redisTemplate.opsForZSet().rangeByScoreWithScores(setName, 0, scoreToFetch);
        Iterator<ZSetOperations.TypedTuple<T>> itr = fetchedSet.iterator();
        Set<T> returnedSet = new LinkedHashSet<T>();
        while (itr.hasNext()) {
            ZSetOperations.TypedTuple<T> typedTupleObj = itr.next();
            returnedSet.add(typedTupleObj.getValue());
        }
        return returnedSet;
    }

    /**
     * This method is used to remove data from set
     * @param setName - A predefined redis set from which data will be removed
     * @param data - Object which will be removed from set
     * @return
     */
    public long removeFromSet(String setName, T data){
        return redisTemplate.opsForZSet().remove(setName, data);
    }
}

