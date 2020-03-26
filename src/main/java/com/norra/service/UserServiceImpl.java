package com.norra.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.norra.constants.Constants;
import com.norra.entities.User;
import com.norra.exceptions.CustomAppException;
import com.norra.model.request.UserSignupRequest;
import com.norra.model.response.CommonErrorResponse;
import com.norra.model.response.ResponseModel;
import com.norra.repositories.UserRepository;
import com.norra.validation.UserValidation;

@Service
public class UserServiceImpl implements UserService{

	private UserValidation userValidation;
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserValidation userValidation, UserRepository userRepository) {
		this.userValidation = userValidation;
		this.userRepository = userRepository;
	}
	
	
	@Override
	public ResponseModel signupUser(UserSignupRequest request) {
		ResponseModel resp = new ResponseModel();
		CommonErrorResponse validation = userValidation.signupValidation(request);
		if(validation !=null && validation.isValid()) {
			User user = new User();
			user.setEmailId(request.getEmailId());
			user.setFullName(request.getFullName());
			user.setProfileImage(request.getProfileImage());
			user.setFcmToken(request.getFcmToken());
			user.setShortBio(request.getShortBio());
			user.setCreatedOn(new Date().getTime());
			user.setUpdatedOn(new Date().getTime());
			user.setMobileNo(request.getMobileNo());
			user.setSource(request.getSource());
			userRepository.save(user);
			resp.setStatus(Constants.STATUS_SUCCESS);
			resp.setMessage(Constants.STATUS_SUCCESS);
			return resp;
		}
		throw new CustomAppException(validation.getHttpStatus(), validation.getMessage(),
				validation.getAppError());
	}

}
