package com.norra.util;

import org.springframework.stereotype.Service;

import com.norra.constants.Constants;
import com.norra.constants.ScreenNameConstants;
import com.norra.model.request.NotificationData;

@Service
public class NotificationTemplateUtil {

	public NotificationData getTemplate(String notificationType) {

		NotificationData notificationData = new NotificationData();
		notificationData.setName_id("");
		switch (notificationType) {
			case Constants.IN_TRIP:
			case Constants.COMPLETED:
				notificationData.setPool_screen_name(ScreenNameConstants.TRIP_SCREEN);
				break;
			
			default:
				notificationData.setPool_screen_name(ScreenNameConstants.HOME_SCREEN);
		}
		return notificationData;
	}
}
