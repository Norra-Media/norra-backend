package com.norra.service;

import com.norra.model.request.UserSignupRequest;
import com.norra.model.response.ResponseModel;

public interface UserService {
	
	ResponseModel signupUser(UserSignupRequest request);

}
