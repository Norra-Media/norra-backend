
package com.norra.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")
public class User extends GenericEntity {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private String emailId;
    private String mobileNo;
    private String shortBio;
    private Date dateOfBirth;
    private String userState;
    private String profileImage;
    private String source;
    private String fcmToken;
    private String loginState;
    private String googleToken;
    private String fbToken;
    private String otp;

}
