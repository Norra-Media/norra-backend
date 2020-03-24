package com.norra.model.request;

import com.norra.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRequest {

    private UserType requestingForUserType;
    private Long requestingForRideId;
    private Long requestingByRideId;
}
