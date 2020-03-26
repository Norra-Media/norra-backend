package com.norra.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequest {

    private String fullName;
    private String emailId;
    private String profileImage;
    private String token;
    private String source;
    private String dateOfBirth;
    private String mobileNo;
    private String fcmToken;
    private String shortBio;

}
