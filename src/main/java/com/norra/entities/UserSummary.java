package com.norra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSummary extends GenericEntity{

    @Min(1)
    @Column(unique = true)
    private Long userId;


    private int questionsAsked;
    
    private int noOfAnswers;
    
    private int noOfPosts;

}
