package com.norra.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.norra.entities.AppConfig;
import com.norra.enums.AppConfigType;
import com.norra.model.response.ResponseModel;
import com.norra.service.AppConfigService;

@RestController
@RequestMapping("/api/appConfig")
public class AppConfigController {

    @Autowired
    private AppConfigService appConfigService;

    /**
     * This api is used to add appConfigs to the database.
     * @param : appConfigs - appConfigs of type Object
     * @return : returns new appConfigs object
     **/
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> addAppConfigs(@Valid @RequestBody List<AppConfig> appConfigs) {
        ResponseModel responseModel = appConfigService.addAppConfig(appConfigs);
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }

    /**
     * This api is returns all the app configs
     * @return : returns list of appConfigList objects
     **/
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel> appConfigList() {
        ResponseModel responseModel = appConfigService.appConfigs();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    /**
     * This api is returns the appConfigs for particular configType
     * @return returns list of appConfigs objects
     */
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel> configTypes(@Valid @RequestParam("configType") AppConfigType configType,  @RequestParam("geoId") Long geoId) {
        ResponseModel responseModel = appConfigService.getAppConfigListByConfigType(configType, geoId);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
