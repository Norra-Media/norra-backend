package com.norra.controller.exceptionhandlers;

import org.hibernate.exception.ConstraintViolationException;
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
import com.norra.exceptions.EntityNotFoundException;
import com.norra.model.response.AppErrorDetails;
import com.norra.model.response.ResponseModel;


@ControllerAdvice
@Component
public class EntityNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * handle hibernate's ConstraintViolationExceptions
     * @param exception
     * @return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseModel> handleException(EntityNotFoundException exception){
        AppErrorDetails appErrorDetails = new AppErrorDetails(
                AppErrorCodes.E400.toString(),
                Constants.BAD_REQUEST,
                exception.getMessage()

        );
        return new ResponseEntity<>(
                new ResponseModel(Constants.STATUS_FAILED,
                        Constants.DATA_NOT_FOUND,
                        null,
                       appErrorDetails),
                HttpStatus.BAD_REQUEST);
    }

}
