package com.norra.model.response;

import org.springframework.http.HttpStatus;
import com.norra.entities.AppConfig;
import com.norra.model.request.User;

import lombok.Data;

@Data
public class CommonErrorResponse {

	private boolean valid;
	private String message;
	private HttpStatus httpStatus;
	private AppError appError;
	private AppConfig appConfig;
	private User user;
}
