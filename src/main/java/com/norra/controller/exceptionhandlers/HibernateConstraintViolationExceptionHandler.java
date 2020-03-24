package com.norra.controller.exceptionhandlers;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONException;
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
import com.norra.util.AppMsgReaderUtil;

@Slf4j
@ControllerAdvice
@Component
public class HibernateConstraintViolationExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * handle hibernate's ConstraintViolationExceptions
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseModel> handleException(ConstraintViolationException exception){
        String errorMsg = exception.getSQLException().getMessage();
        AppErrorDetails appErrorDetails = new AppErrorDetails(
                AppErrorCodes.E401.toString(),
                AppMsgReaderUtil.getTextValue(Constants.CONSTRAINT_VIOLATION_MSG),
                errorMsg.substring(errorMsg.indexOf("Detail:") + 8)

        );
        return new ResponseEntity<>(
                new ResponseModel(Constants.STATUS_FAILED,
                        Constants.CONSTRAINT_VIOLATION,
                        null,
                       appErrorDetails),
                HttpStatus.BAD_REQUEST);
    }

}
