package com.norra.cache.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.norra.cache.CacheQueueName;
import com.norra.cache.util.CacheUtility;

import java.util.Date;
import java.util.Set;

@Slf4j
@Configuration
public class CacheSchedulerManagerImpl<T> implements CacheSchedulerManager {

    private CacheUtility cacheUtility;

    @Autowired
    CacheSchedulerManagerImpl(CacheUtility cacheUtility){
        this.cacheUtility = cacheUtility;
    }

    /**
     * This method is used to add scheduled tasks corresponding to ride in Redis
     *
     * @param rideId
     * @param cacheQueue    - queue name in which the scheduled task needs to be stored
     * @param timeToExecute - Epoch time when the scheduled task should be executed
     */
    @Override
    public void addScheduledTaskForRide(Long rideId, CacheQueueName cacheQueue, Long timeToExecute) {
        log.debug("Adding scheduled task for ride {} in cache set {}",rideId,cacheQueue.toString());
        this.cacheUtility.addToSet(cacheQueue.toString(),rideId,timeToExecute);
        log.debug("Completed adding scheduled task for ride {} in cache set {}",rideId,cacheQueue.toString());
    }

    /**
     * This method is used to fetch scheduled tasks based on the lapsed epoch time
     *
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     * @return - Returns a set of rideIds
     */
    @Override
    public Set getScheduledTaskForRide(CacheQueueName cacheQueue) {
        Date date = new Date();
        Long currEpochTime = date.getTime();
        log.debug("Fetching scheduled tasks for epoch time = "+currEpochTime);
        Set rideIdSet = this.cacheUtility.getFromSetBasedOnScore(cacheQueue.toString(),currEpochTime);
        log.debug("Fetched ride Ids from cache = "+rideIdSet);

        //Remove the rideIds from cache which have already been fetched
        for(Object id : rideIdSet){
            Long rideId = ((Integer)id).longValue();
            log.debug("getScheduledTaskForRide:Removing ride id {} from cache",rideId);
            removeScheduledTaskForRide(rideId,cacheQueue);
        }
        log.debug("getScheduledTaskForRide method execution completed");
        return rideIdSet;
    }

    /**
     * This method is used to remove a task from scheduler based on rideId
     *
     * @param rideId
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     */
    @Override
    public void removeScheduledTaskForRide(Long rideId, CacheQueueName cacheQueue) {
        log.debug("Removing scheduled task for ride id {} from queue {}",rideId,cacheQueue);
        long id = this.cacheUtility.removeFromSet(cacheQueue.toString(),rideId);
        log.debug("removeScheduledTaskForRide method execution completed with cache id {} ",id);
    }

    /**
     * This method is used to add scheduled tasks corresponding to trip in Redis
     *
     * @param tripId
     * @param cacheQueue    - queue name in which the scheduled task needs to be stored
     * @param timeToExecute - Epoch time when the scheduled task should be executed
     */
    @Override
    public void addScheduledTaskForTrip(Long tripId, CacheQueueName cacheQueue, Long timeToExecute) {
        log.debug("Adding scheduled task for trip {} in cache set {}",tripId,cacheQueue.toString());
        this.cacheUtility.addToSet(cacheQueue.toString(),tripId,timeToExecute);
        log.debug("Completed adding scheduled task for trip {} in cache set {}",tripId,cacheQueue.toString());

    }

    /**
     * This method is used to fetch scheduled tasks based on the lapsed epoch time
     *
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     * @return - Returns a set of tripIds
     */
    @Override
    public Set getScheduledTaskForTrip(CacheQueueName cacheQueue) {
        Date date = new Date();
        Long currEpochTime = date.getTime();
        log.debug("Fetching scheduled tasks for epoch time = "+currEpochTime);
        Set tripIdSet = this.cacheUtility.getFromSetBasedOnScore(cacheQueue.toString(),currEpochTime);
        log.debug("Fetched Trip Ids from cache = "+tripIdSet);

        //Remove the tripIds from cache which have already been fetched
        for(Object id : tripIdSet){
            Long tripId = ((Integer)id).longValue();
            log.debug("getScheduledTaskForTrip:Removing trip id {} from cache",tripId);
            removeScheduledTaskForTrip(tripId,cacheQueue);
        }
        log.debug("getScheduledTaskForTrip method execution completed");
        return tripIdSet;
    }

    /**
     * This method is used to remove a task from scheduler based on tripId
     *
     * @param tripId
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     */
    @Override
    public void removeScheduledTaskForTrip(Long tripId, CacheQueueName cacheQueue) {
        log.debug("Removing scheduled task for trip id {} from queue {}",tripId,cacheQueue);
        long id = this.cacheUtility.removeFromSet(cacheQueue.toString(),tripId);
        log.debug("removeScheduledTaskForTrip method execution completed with cache id {} ",id);
    }
    /**
     * This method is used to add scheduled clear due push notification tasks corresponding to ride in Redis
     *
     * @param rideId
     * @param cacheQueue    - queue name in which the scheduled task needs to be stored
     * @param timeToExecute - Epoch time when the scheduled task should be executed
     */
    @Override
    public void addScheduledTaskForClearDuesPN(Long rideId, CacheQueueName cacheQueue, Long timeToExecute) {
        log.debug("Adding scheduled task for clear dues push notification for ride {} in cache set {}",rideId, cacheQueue.toString());
        this.cacheUtility.addToSet(cacheQueue.toString(), rideId, timeToExecute);
        log.debug("Completed adding scheduled task for clear dues push notification for ride {} in cache set {}",rideId, cacheQueue.toString());
    }
    /**
     * This method is used to fetch scheduled tasks based on the lapsed epoch time
     *
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     * @return - Returns a set of rideIds
     */
    @Override
    public Set<Long> getScheduledTaskForDues(CacheQueueName cacheQueue) {
        Date date = new Date();
        Long currEpochTime = date.getTime();
        log.debug("Fetching scheduled clear dues tasks for epoch time = "+currEpochTime);
        Set rideIdSet = this.cacheUtility.getFromSetBasedOnScore(cacheQueue.toString(),currEpochTime);
        log.debug("Fetched ride Ids from cache = "+rideIdSet);

        //Remove the rideIds from cache which have already been fetched
        for(Object id : rideIdSet){
            Long rideId = ((Integer)id).longValue();
            log.debug("getScheduledTaskForDues:Removing ride id {} from cache",rideId);
            removeScheduledTaskForDues(rideId,cacheQueue);
        }
        log.debug("getScheduledTaskForDues method execution completed");
        return rideIdSet;
    }

    /**
     * This method is used to remove a task from scheduler based on rideId
     *
     * @param rideId
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     */
    @Override
    public void removeScheduledTaskForDues(Long rideId, CacheQueueName cacheQueue) {
        log.debug("Removing scheduled clear dues task for ride id {} from queue {}",rideId,cacheQueue);
        long id = this.cacheUtility.removeFromSet(cacheQueue.toString(),rideId);
        log.debug("removeScheduledTaskForDues method execution completed with cache id {} ",id);
    }
}
