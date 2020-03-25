
package com.norra.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.norra.enums.AppConfigKey;
import com.norra.enums.AppConfigType;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vote")
public class Vote extends GenericEntity {

    private static final long serialVersionUID = 1L;

    private boolean upvote;
    
    private Long voteByUserId;

}
