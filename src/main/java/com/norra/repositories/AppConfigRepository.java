package com.norra.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.norra.entities.AppConfig;
import com.norra.enums.AppConfigKey;
import com.norra.enums.AppConfigType;

@Repository
public interface AppConfigRepository extends JpaRepository<AppConfig, Long> {

    /**
     * This query is returns the app config for particular config type
     * @param configType
     * @return returns list of app config objects
     */
    @Query("SELECT appconfig FROM AppConfig appconfig WHERE appconfig.configType = ?1")
    List<AppConfig> findByConfigType(AppConfigType configType);
    
    
    /**
     * This query is returns the app config for particular config type
     * @param configType
     * @param geoId
     * @return returns list of app config objects
     */
    @Query("SELECT appconfig FROM AppConfig appconfig WHERE appconfig.configType = ?1")
    List<AppConfig> findByConfigTypeGeoId(AppConfigType configType);
    
    
    /**
     * Find by config type key geo id.
     *
     * @param configType the config type
     * @param key the key
     * @param geoId the geo id
     * @return the app config
     */
    @Query("SELECT appconfig FROM AppConfig appconfig WHERE appconfig.configType = ?1 and appconfig.key = ?2")
    AppConfig findByConfigTypeKeyGeoId(AppConfigType configType, AppConfigKey key);
}
