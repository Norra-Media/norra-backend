package com.norra.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.norra.constants.Constants;
import com.norra.entities.AppConfig;
import com.norra.enums.AppConfigKey;
import com.norra.enums.AppConfigType;
import com.norra.model.response.ResponseModel;
import com.norra.repositories.AppConfigRepository;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@Slf4j
@Service
public class AppConfigServiceImpl implements AppConfigService {

    @Autowired
    private AppConfigRepository appConfigRepository;

    /**
     * This method is used to add app config to the database.
     * @param : appConfigs - App Config of type Object
     * @return : returns new App config list object
     **/
    @Override
    public ResponseModel addAppConfig(List<AppConfig> appConfigs) {
        if (appConfigs == null) {
            return new ResponseModel(Constants.BAD_REQUEST, Constants.BAD_REQUEST, null, null);
        }
        List<AppConfig> appConfigListObj = new ArrayList<>();
        for (AppConfig appConfig : appConfigs) {
            appConfig.setConfigType(appConfig.getConfigType());
            appConfig.setKey(appConfig.getKey());
            appConfig = appConfigRepository.save(appConfig);
            appConfigListObj.add(appConfig);
        }
        return new ResponseModel(Constants.STATUS_SUCCESS, Constants.APP_CONFIG_DETAILS, appConfigListObj, null);
    }

    /**
     * This method is returns all the app configs
     * @return : returns list of app config list objects
     **/
    @Override
    public ResponseModel appConfigs() {
        List<AppConfig> appConfigList = appConfigRepository.findAll();
        return new ResponseModel(Constants.STATUS_SUCCESS, Constants.APP_CONFIG_DETAILS, appConfigList, null);
    }

    /**
     * prepare the app config map based on config type and geoId.
     *
     * @param configType the config type
     * @param geoId the geo id
     * @return the app config map by config type
     */
    @Override
    public HashMap<AppConfigKey, String> getAppConfigMapByConfigType(AppConfigType configType) {
    	HashMap<AppConfigKey, String> configs = new HashMap<AppConfigKey, String>();
    	List<AppConfig> configList = appConfigRepository.findByConfigTypeGeoId(configType);
    	for (AppConfig appConfig : configList) {
    		configs.put(appConfig.getKey(), appConfig.getValue());
		}
    	return configs;
    }

	
	/**
	 * Gets the app config by type key and geo id.
	 *
	 * @param configType the config type
	 * @param key the key
	 * @param geoId the geo id
	 * @return the app config by type key and geo id
	 */
	@Override
	public AppConfig getAppConfigByTypeKeyAndGeoId(AppConfigType configType, AppConfigKey key) {
		return appConfigRepository.findByConfigTypeKeyGeoId(configType, key);
	}

	
	@Override
	public ResponseModel getAppConfigListByConfigType(@Valid AppConfigType configType, Long geoId) {
		ResponseModel model = new ResponseModel();
		List<AppConfig> configList = appConfigRepository.findByConfigTypeGeoId(configType);
		model.setData(configList);
		model.setStatus(Constants.STATUS_SUCCESS);
		model.setMessage("app config list");
		return model;
	}

}
