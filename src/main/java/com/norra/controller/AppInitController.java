package com.norra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.norra.service.AppInitService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/appInit/")
public class AppInitController {
    AppInitService appInitService;

    @Autowired
    public AppInitController(AppInitService appInitService) {
        this.appInitService = appInitService;
    }

    
}
