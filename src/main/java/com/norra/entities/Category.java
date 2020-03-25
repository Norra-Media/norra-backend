
package com.norra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category extends GenericEntity {

    private static final long serialVersionUID = 1L;

    @Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
    private String Category_metadata;

}
