
package com.norra.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.norra.enums.AppConfigKey;
import com.norra.enums.AppConfigType;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	private String commentMetaData;

	private Long storyId;

	private Long commentByUserId;

}
