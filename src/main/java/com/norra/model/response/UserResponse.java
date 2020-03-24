package com.norra.model.response;

import lombok.Data;

@Data
public class UserResponse {
    private String profileUrl;
    private String firstName;
    private String lastName;
    private Long userId;
    private double rating;
}
