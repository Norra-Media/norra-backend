package com.norra.core.filter.constraintmap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class ConstrainMapDeserializer extends JsonDeserializer<ConstraintMap> implements ApplicationContextAware {

    private Map<String, Class> entities = new HashMap<>();
    private ApplicationContext context;

    /**
     * Deserialize from string format to ConstraintMap class
     * @param jsonParser
     * @param deserializationContext
     * @return
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public ConstraintMap deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ConstraintMap constraintMap = new ConstraintMap();

        String currentFieldName;
        while ((currentFieldName = jsonParser.nextFieldName()) != null) {
            String[] entityColumn = currentFieldName.split("\\.");
            ConstraintMapping constraintMapping;
            Class entityClass = this.entities.get(entityColumn[0]);

            if (entityClass == null) continue;

            try {
                constraintMapping = new ConstraintMapping(entityClass, entityColumn[1], jsonParser.getCodec().readTree(jsonParser).get(currentFieldName).toString());
            } catch (NoSuchFieldException | InvalidConstraintValueException e) {
                continue;
            }

            constraintMap.add(constraintMapping);
        }

        return constraintMap;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        this.context.getBean(EntityManager.class)
                .getMetamodel().getEntities()
                .forEach(e -> entities.put(e.getName(), e.getBindableJavaType()));
    }
}
