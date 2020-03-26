package com.norra.controller;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.norra.constants.Constants;
import com.norra.exceptions.CustomAppException;
import com.norra.model.request.UserSignupRequest;
import com.norra.model.response.ResponseModel;
import com.norra.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user/")
public class UserController {

	UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * This api is used to signup user .
	 * 
	 * @param userProfileRequest
	 * @return returns the User profile details
	 */
	@PostMapping(value = "signup")
	public ResponseEntity<ResponseModel> userSignup(@RequestBody UserSignupRequest request) {
		try {
			return new ResponseEntity<ResponseModel>(userService.signupUser(request), HttpStatus.OK);
		} catch (CustomAppException e) {
			return new ResponseEntity<>(new ResponseModel(Constants.STATUS_FAILED, e.getAddtionalMessage(), null,
					Collections.singletonList(e.getAddtionalMessage())), e.getHttpStatus());
		}
	}

}
