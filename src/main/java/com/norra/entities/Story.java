
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
@Table(name = "story")
public class Story extends GenericEntity {

    private static final long serialVersionUID = 1L;

    private String story_type;

    @Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
    private String story_content;

    private Long storyByUserId;
    
    private Long categoryId;


}
