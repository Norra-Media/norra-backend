package com.norra.core.filter.constraintmap;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

class ConstrainMapSerializer extends JsonSerializer<ConstraintMap> {

    /**
     * Serialize constraint map
     * @param constraintMap
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(ConstraintMap constraintMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        for (ConstraintMapping cm : constraintMap.getConstraintMapList()) {
            jsonGenerator.writeObjectField(cm.getEntityClass().getName() + "." + cm.getColumn(), cm.getValue());
        }

        jsonGenerator.writeEndObject();
    }
}
