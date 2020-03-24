package com.norra.core.filter.constraintmap;

public class InvalidConstraintValueException extends Exception {

    public InvalidConstraintValueException(String entityClassName, String columnName, Object value){
        super("Invalid value provided for entity[" + entityClassName + "]'s column " + columnName + " : " + value);
    }
}
