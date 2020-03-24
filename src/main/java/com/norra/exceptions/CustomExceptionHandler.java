
package com.norra.exceptions;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.norra.constants.Constants;
import com.norra.model.response.ApiError;
import com.norra.model.response.ErrorResponse;
import com.norra.model.response.ResponseModel;


@SuppressWarnings({"unchecked", "rawtypes"})

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	    ExceptionLogger.logError(ex);
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(Constants.SERVER_ERROR, details);
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ExceptionLogger.logError(ex);
        List<String> details = new ArrayList<>();
        details.add(HttpStatus.NOT_FOUND.toString());
        details.add(ex.getLocalizedMessage());
        ResponseModel responseModel = new ResponseModel(Constants.STATUS_FAILED, ex.getLocalizedMessage(), null, details);
        return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleArgumentMissingException(BadRequestException ex, WebRequest request) {
        ExceptionLogger.logError(ex);
        List<String> details = new ArrayList<>();
        details.add(HttpStatus.BAD_REQUEST.toString());
        details.add(ex.getLocalizedMessage());
        ResponseModel responseModel = new ResponseModel(Constants.STATUS_FAILED, ex.getLocalizedMessage(), null, details);
        return new ResponseEntity(responseModel, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionLogger.logError(ex);
        List<String> errors = new ArrayList<String>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            StringBuffer errorMsg = new StringBuffer();
            errorMsg.append(error.getField());
            errorMsg.append(Constants.COLON_SPACE);
            errorMsg.append(error.getDefaultMessage());
            errors.add(errorMsg.toString());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            StringBuffer errorMsg = new StringBuffer();
            errorMsg.append(error.getObjectName());
            errorMsg.append(Constants.COLON_SPACE);
            errorMsg.append(error.getDefaultMessage());
            errors.add(errorMsg.toString());
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionLogger.logError(ex);
        String error = ex.getParameterName() + " parameter is missing";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        ExceptionLogger.logError(ex);
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            StringBuffer errorMsg = new StringBuffer();
            errorMsg.append(violation.getRootBeanClass().getName());
            errorMsg.append(Constants.SPACE);
            errorMsg.append(violation.getPropertyPath());
            errorMsg.append(Constants.COLON_SPACE);
            errorMsg.append(violation.getMessage());
            errors.add(errorMsg.toString());
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                   WebRequest request) {
        ExceptionLogger.logError(ex);
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
