package com.norra.exceptions;

import lombok.Data;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;

import com.norra.model.response.AppError;

@Data
public class CustomAppException extends NestedRuntimeException {

    private static final long serialVersionUID = 1L;
    private HttpStatus httpStatus;
    private String addtionalMessage;
    private String status;
    private AppError appError;

    public CustomAppException(HttpStatus httpStatus, String addtionalMessage, Throwable cause) {
        super(addtionalMessage, cause);
        this.httpStatus = httpStatus;
        this.addtionalMessage = addtionalMessage;
    }

    public CustomAppException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public CustomAppException(HttpStatus httpStatus, String addtionalMessage, AppError appError) {
        super(addtionalMessage);
        this.httpStatus = httpStatus;
        this.addtionalMessage = addtionalMessage;
        this.appError = appError;
    }

    public CustomAppException(String addtionalMessage) {
        super(addtionalMessage);
        this.addtionalMessage = addtionalMessage;
    }
}