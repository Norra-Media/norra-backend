package com.norra.model.response;

import com.norra.enums.Status;

import lombok.Data;

@Data
public class NotificationCheckResponse {
    private Status status;
}
