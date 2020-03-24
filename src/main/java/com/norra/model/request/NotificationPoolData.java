package com.norra.model.request;

import lombok.Data;

@Data
public class NotificationPoolData {

    private String notificationType;
    private String message;
    private String screenName;
    private String title;

}
