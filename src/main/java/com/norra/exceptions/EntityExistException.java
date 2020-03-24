package com.norra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityExistException extends RuntimeException {

    public EntityExistException(String msg) {
        super(msg);
    }
}
