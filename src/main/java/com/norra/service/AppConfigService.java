package com.norra.service;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import com.norra.entities.AppConfig;
import com.norra.enums.AppConfigKey;
import com.norra.enums.AppConfigType;
import com.norra.model.response.ResponseModel;

public interface AppConfigService {

    /**
     * This method is used to add app config to the database.
     * @param : appConfigs - App Config of type Object
     * @return : returns new appConfig object
     **/
    ResponseModel addAppConfig(List<AppConfig> appConfigs);

    /**
     * This method is returns all the app config
     * @return : returns list of app config List objects
     **/
    ResponseModel appConfigs();

	/**
	 * prepare the app config map by config type and geoId.
	 *
	 * @param configType the config type
	 * @param geoId the geo id
	 * @return the app config map by config type
	 */
	HashMap<AppConfigKey, String> getAppConfigMapByConfigType(AppConfigType configType);
	
	
	
	/**
	 * Gets the app config by type key and geo id.
	 *
	 * @param configType the config type
	 * @param key the key
	 * @param geoId the geo id
	 * @return the app config by type key and geo id
	 */
	AppConfig getAppConfigByTypeKeyAndGeoId(AppConfigType configType, AppConfigKey key);
	
	/**
	 * Gets the app config list by config type.
	 *
	 * @param configType the config type
	 * @param geoId the geo id
	 * @return the app config list by config type
	 */
	ResponseModel getAppConfigListByConfigType(@Valid AppConfigType configType, Long geoId);
	
	
}
