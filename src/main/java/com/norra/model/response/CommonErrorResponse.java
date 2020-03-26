package com.norra.model.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class CommonErrorResponse {

	private boolean valid;
	private String message;
	private HttpStatus httpStatus;
	private AppError appError;
}
