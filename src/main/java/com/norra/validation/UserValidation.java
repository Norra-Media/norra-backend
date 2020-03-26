package com.norra.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.norra.entities.User;
import com.norra.enums.AppErrorCodes;
import com.norra.model.request.UserSignupRequest;
import com.norra.model.response.AppError;
import com.norra.model.response.CommonErrorResponse;
import com.norra.repositories.UserRepository;

import io.sentry.util.Util;


@Service
public class UserValidation {
	
	@Autowired
	private UserRepository userRepository;
	
	public CommonErrorResponse signupValidation(UserSignupRequest request) {
		CommonErrorResponse response = new CommonErrorResponse();
		if(request == null) {
			response.setValid(false);
			response.setMessage("Invalid request");
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
			AppError error = new AppError(AppErrorCodes.E400.getValue(), response.getMessage());
			response.setAppError(error);
			return response;
		}
		if(Util.isNullOrEmpty(request.getEmailId())) {
			response.setValid(false);
			response.setMessage("Email Id required.");
			response.setHttpStatus(HttpStatus.OK);
			AppError error = new AppError(AppErrorCodes.E200.getValue(), response.getMessage());
			response.setAppError(error);
			return response;
		}
		User user = userRepository.findByEmailId(request.getEmailId());
		if(user !=null) {
			response.setValid(false);
			response.setMessage("User already exist with this email, please login.");
			response.setHttpStatus(HttpStatus.OK);
			AppError error = new AppError(AppErrorCodes.E200.getValue(), response.getMessage());
			response.setAppError(error);
			return response;
		}
		response.setValid(true);
		return response;
		
		
	}


}
