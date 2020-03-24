package com.norra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    public enum Type{
        ID,
        LOCATION
    }

    public EntityNotFoundException(String msg) {
        super(msg);
    }

    public EntityNotFoundException(Type type, String params){
        super("Entity with " + type + " " + params + " not found");
    }

    public EntityNotFoundException(String attribute, String[] names){
        super("Entities with "+ attribute + " not found : " + Arrays.toString(names));
    }

    public <T> EntityNotFoundException(Class<T> clazz, Type type, String params){
        super("Entity " + clazz.getName() + " with " + type + " " + params + " not found");
    }

}
