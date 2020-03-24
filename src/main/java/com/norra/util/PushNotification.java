package com.norra.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.norra.constants.Constants;
import com.norra.model.request.NotificationData;
import com.norra.model.request.NotificationPoolData;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
@Component
public class PushNotification {

	@Value("${push-notification-corona-server-key}")
	private String coronaServerKey;
	
	@Value("${push-notification-fcm-google-endpoint}")
	private String fcmGoogleEndPoint;

	@Value("${push-notification-google-server-key}")
	private String googleServerKey;
	
	@Autowired
	NotificationTemplateUtil notificationTemplateUtil;

	private final MediaType mediaType = MediaType.parse(Constants.MEDIA_TYPE);

	/**
	 * It is used to send notification to FCM server using okhttp.
	 */
	public void sendNotification(String fcmToken, String notificationTitle, String notificationBody,
			NotificationPoolData poolData) {
		NotificationData data = new NotificationData();
		data = notificationTemplateUtil.getTemplate(poolData.getNotificationType());
		poolData.setScreenName(data.getPool_screen_name());
		data.setPool_data(poolData);
		boolean isGoogle = true;
		if (fcmToken != null) {
			String response = "";
			if (notificationTitle != null && notificationBody != null && notificationTitle != ""
					&& notificationBody != "") {
				String jsonMessage = "";
				if(isGoogle) {
				jsonMessage = getPayloadForGoogleFCM(notificationTitle, notificationBody, fcmToken, data);
				}else {
				jsonMessage = getPayloadForCoronaApi(notificationTitle, notificationBody, fcmToken, data);
				}
				log.info("notification sent to " + fcmToken + ": response:" + jsonMessage);
				response = sendMessageToFcm(jsonMessage, fcmToken, isGoogle);
				log.info("response from fcm:" + response);
			}
		}else {
			log.info("no fcm token found");
		}
		}

	/**
	 * This method returns the notification message payload for corona api at bouce.
	 * 
	 * @param title of type string
	 * @param msg   of type message
	 * @return returns the notification string
	 */
	private String getPayloadForCoronaApi(String title, String msg, String clientToken, NotificationData data) {
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("type", "notification");
		jsonObj.addProperty("legacy", "0");
		// client registration key is sent as token in the message to FCM server
		jsonObj.addProperty("fcm_token", clientToken);

		JsonObject notification = new JsonObject();
		notification.addProperty("title", title);
		notification.addProperty("body", msg);

		Gson gson = new Gson();
		String jsonMessage = gson.toJson(data);
		JsonParser jsonParser = new JsonParser();
		JsonObject dataObject = (JsonObject) jsonParser.parse(jsonMessage);

		JsonObject content = new JsonObject();
		content.add("notification", notification);
		content.add("data", dataObject);

		jsonObj.add("content", content);
		log.info("notification json for corona:" + jsonObj.toString());
		return jsonObj.toString();
	}

	/**
	 * This method is used to send message to firebase cloud messaging server using
	 * okhttp
	 * 
	 * @param jsonMessage - of type String
	 */
	private String sendMessageToFcm(String jsonMessage, String fcmToken, boolean isGoogleFcm) {
		String responseStr = null;
		OkHttpClient httpClient = new OkHttpClient();
		Response response = null;
		String headerKey = isGoogleFcm ? Constants.AUTH_HEADER_TOKEN : Constants.HEADER_TOKEN;
		String headerValue = isGoogleFcm ? googleServerKey : coronaServerKey;
		String endPoint =  fcmGoogleEndPoint;
		try {
			Request request = new Request.Builder().url(endPoint)
					.addHeader(Constants.CONTENT_TYPE, "application/json; UTF-8")
					.addHeader(headerKey, headerValue).post(RequestBody.create(mediaType, jsonMessage))
					.build();
			response = httpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				log.info("fcm notification result for token :" + fcmToken + " :" + response.body().toString());
				responseStr = "Message has been sent to FCM server";
			} else {
				responseStr = "Message has not been sent to FCM server";
			}
			response.close();
		} catch (IOException e) {
			responseStr = "Message has not been sent to FCM server";
		} finally {
			response.close();
		}
		log.info("Response String", responseStr);
		return responseStr;

	}
	
	
	
	/**
	 * This method returns the notification message payload for fcm google api.
	 * 
	 * @param title of type string
	 * @param msg   of type message
	 * @return returns the notification string
	 */
	private String getPayloadForGoogleFCM(String title, String msg, String clientToken, NotificationData data) {
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("to", clientToken);
		JsonObject notification = new JsonObject();
		notification.addProperty("title", title);
		notification.addProperty("body", msg);

		Gson gson = new Gson();
		String jsonMessage = gson.toJson(data);
		JsonParser jsonParser = new JsonParser();
		JsonObject dataObject = (JsonObject) jsonParser.parse(jsonMessage);

		jsonObj.add("notification", notification);
		jsonObj.add("data", dataObject);
		log.info("notification json for google fcm:" + jsonObj.toString());
		return jsonObj.toString();
	}

}
