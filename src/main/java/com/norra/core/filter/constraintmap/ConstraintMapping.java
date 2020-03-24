package com.norra.core.filter.constraintmap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.lang.reflect.Field;

@Data
public class ConstraintMapping {

    @JsonIgnore
    private Class entityClass;

    @JsonIgnore
    private String column;

    @JsonIgnore
    private Object value;

    /**
     * Checks for fields and validates class
     * @param entityClass
     * @param column
     * @param value
     * @throws NoSuchFieldException
     * @throws InvalidConstraintValueException
     */
    public ConstraintMapping(Class entityClass, String column, Object value)
            throws NoSuchFieldException, InvalidConstraintValueException {
        Field f;
        try {
            f = entityClass.getDeclaredField(column);
        } catch (NoSuchFieldException e) {
            f = entityClass.getSuperclass().getDeclaredField(column);
        }
        // TODO: add back constraint
//        try {
//            f.getType().cast(value);
//        } catch (Exception e){
//            throw new InvalidConstraintValueException(entityClass.getName(), column, value);
//        }

        this.entityClass = entityClass;
        this.column = column;
        this.value = value;
    }

}