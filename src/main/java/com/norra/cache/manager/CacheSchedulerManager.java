package com.norra.cache.manager;

import java.util.Set;

import com.norra.cache.CacheQueueName;

public interface CacheSchedulerManager {

    /**
     * This method is used to add scheduled tasks corresponding to ride in Redis
     * @param rideId
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     * @param timeToExecute - Epoch time when the scheduled task should be executed
     */
    void addScheduledTaskForRide(Long rideId, CacheQueueName cacheQueue, Long timeToExecute);

    /**
     * This method is used to fetch scheduled tasks
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     * @return - Returns a set of rideIds
     */
    Set<Long> getScheduledTaskForRide(CacheQueueName cacheQueue);

    /**
     * This method is used to remove a task from scheduler based on rideId
     * @param rideId
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     */
    void removeScheduledTaskForRide(Long rideId, CacheQueueName cacheQueue);

    /**
     * This method is used to add scheduled tasks corresponding to trip in Redis
     * @param tripId
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     * @param timeToExecute - Epoch time when the scheduled task should be executed
     */
    void addScheduledTaskForTrip(Long tripId, CacheQueueName cacheQueue, Long timeToExecute);

    /**
     * This method is used to fetch scheduled tasks
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     * @return - Returns a set of rideIds
     */
    Set<Long> getScheduledTaskForTrip(CacheQueueName cacheQueue);

    /**
     * This method is used to remove a task from scheduler based on tripId
     * @param tripId
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     */
    void removeScheduledTaskForTrip(Long tripId, CacheQueueName cacheQueue);

    /**
     * This method is used to add scheduled tasks corresponding to ride in Redis
     * @param rideId
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     * @param timeToExecute - Epoch time when the scheduled task should be executed
     */
    void addScheduledTaskForClearDuesPN(Long rideId, CacheQueueName cacheQueue, Long timeToExecute);

    /**
     * This method is used to fetch scheduled tasks
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     * @return - Returns a set of rideIds
     */
    Set<Long> getScheduledTaskForDues(CacheQueueName cacheQueue);

    /**
     * This method is used to remove a task from scheduler based on rideId
     * @param rideId
     * @param cacheQueue - queue name in which the scheduled task needs to be stored
     */
    void removeScheduledTaskForDues(Long rideId, CacheQueueName cacheQueue);

}
