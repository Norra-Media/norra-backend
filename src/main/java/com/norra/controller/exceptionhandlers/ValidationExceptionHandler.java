package com.norra.controller.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.norra.constants.Constants;
import com.norra.enums.AppErrorCodes;
import com.norra.model.response.AppErrorDetails;
import com.norra.model.response.ResponseModel;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Handles all constraint violation exceptions and responds with standard error response
 */
@ControllerAdvice
@Component
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle javax's ConstraintViolationExceptions
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseModel> handleException(ConstraintViolationException exception) {
        AppErrorDetails appErrorDetails = new AppErrorDetails(
                AppErrorCodes.E200.toString(),
                Constants.CONSTRAINT_VIOLATION,
                exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toArray()
        );

        return new ResponseEntity<>(
                new ResponseModel(
                        Constants.STATUS_FAILED,
                        Constants.CONSTRAINT_VIOLATION,
                        null,
                        appErrorDetails
                ),
                HttpStatus.BAD_REQUEST);
    }

}
