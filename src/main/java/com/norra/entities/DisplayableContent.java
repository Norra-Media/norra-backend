package com.norra.entities;

import lombok.Data;
import org.hibernate.annotations.Type;

import com.norra.core.filter.constraintmap.ConstraintMap;
import com.norra.enums.DisplyableContentCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "key"))
public class DisplayableContent extends GenericEntity {
    @NotNull
    protected DisplyableContentCategory category;

    @NotNull
    @Column(unique = true, nullable = false)
    protected String key;

    @NotNull
    protected String title;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    protected List<String> image;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    protected List<String> subInfo;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    protected List<String> content;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    protected ConstraintMap notShownOn;

}