package com.norra.model;

import com.norra.model.request.NotificationPoolData;
import lombok.Data;

@Data
public class NotificationTemplate {
    // device id (registration id) of device (mobile) on which notif will be triggered.
    private String fcmToken;
    // text that will be displayed on device
    private String title;
    // It contains data which is sent in notification body
    private NotificationPoolData notificationPayload;
    // It is a message string
    private String templateName;
    // user name 
    private String name;
    private String action;
}