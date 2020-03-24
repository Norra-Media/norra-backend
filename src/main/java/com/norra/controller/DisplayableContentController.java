package com.norra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.norra.entities.DisplayableContent;
import com.norra.model.response.DisplayableContentResponse;
import com.norra.model.response.ResponseModel;
import com.norra.service.DisplayableContentService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/display-content", produces = MediaType.APPLICATION_JSON_VALUE)
public class DisplayableContentController {
    @Autowired
    private DisplayableContentService service;

    @PostMapping
    public ResponseEntity<ResponseModel> addAppInfo(@Valid @RequestBody DisplayableContent dc) {
        ResponseModel responseModel = new ResponseModel(HttpStatus.CREATED.toString(), "", service.addNew(dc), null);
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }

    @GetMapping(path = "{key}")
    public ResponseEntity<ResponseModel> getAppInfo(@Valid @PathVariable("key") String key) {
        DisplayableContentResponse dcr = new DisplayableContentResponse(service.getByKey(key), service);
        ResponseModel responseModel = new ResponseModel(HttpStatus.OK.toString(), "", dcr, null);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseModel> getAppInfos(@Valid @RequestParam("keys") String keys) {
        String[] splitKeys = keys.split(",");

        List<DisplayableContent> dcs = service.getByKeys(Arrays.asList(splitKeys));

        List<DisplayableContentResponse> responseList = new ArrayList<>();

        dcs.forEach(d -> responseList.add(new DisplayableContentResponse(d, service)));

        ResponseModel responseModel = new ResponseModel(HttpStatus.OK.toString(), "", responseList, null);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
