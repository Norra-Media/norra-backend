package com.norra.core.filter.constraintmap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonSerialize(using = ConstrainMapSerializer.class)
@JsonDeserialize(using = ConstrainMapDeserializer.class)
public class ConstraintMap {

    @JsonIgnore
    private List<ConstraintMapping> constraintMapList = new ArrayList<>();

    public ConstraintMap add(ConstraintMapping constraintMapping) {
        this.constraintMapList.add(constraintMapping);
        return this;
    }
}

