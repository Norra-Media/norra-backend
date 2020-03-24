package com.norra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.norra.constants.Constants;
import com.norra.exceptions.CustomAppException;
import com.norra.model.response.AppInitInfoResponse;
import com.norra.model.response.CommonErrorResponse;
import com.norra.model.response.ResponseModel;
import com.norra.validation.AppInitValidation;

@Service(value = "appInitService")
public class AppInitServiceImpl implements AppInitService {

    private AppInitValidation appInitValidation;

    @Autowired
    public AppInitServiceImpl(AppInitValidation appInitValidation) {
        this.appInitValidation = appInitValidation;
    }

}
