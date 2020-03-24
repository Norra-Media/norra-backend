package com.norra.model.request;

import lombok.Data;

@Data
public class NotificationData {

    private String name_id;
    private String pool_screen_name;
    private NotificationPoolData pool_data;

}

