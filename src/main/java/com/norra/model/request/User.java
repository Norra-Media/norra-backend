package com.norra.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class User {

	@SerializedName(value = "profileUrl", alternate = "image")
	@Expose
	private String profileUrl;

	@SerializedName(value = "firstName", alternate = "first_name")
	@Expose
	private String firstName;

	@SerializedName(value = "lastName", alternate = "last_name")
	@Expose
	private String lastName;

	@SerializedName(value="gender")
	@Expose
	private String gender;

	@SerializedName(value = "id", alternate = "user_id")
	@Expose
	private Long id;

	@SerializedName(value = "mobileNumber", alternate = "mobile_number")
	@Expose
	private String mobileNumber;

	@SerializedName(value="email")
	@Expose
	private String email;

	@SerializedName(value = "companyName", alternate = "org_name")
	@Expose
	private String companyName;

	@SerializedName(value="fcmToken",alternate = "fcm_token")
	@Expose
	private String fcmToken;

	@SerializedName(value="profession")
	@Expose
	private String profession;
	
	@SerializedName(value="token")
	@Expose
	private String token;
}
