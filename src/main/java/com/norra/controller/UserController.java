package com.norra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.norra.constants.Constants;
import com.norra.exceptions.CustomAppException;
import com.norra.model.request.User;
import com.norra.model.response.ResponseModel;
import com.norra.service.UserSummaryService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user/")
public class UserController {

	@Autowired
	public UserController() {
	}

}
