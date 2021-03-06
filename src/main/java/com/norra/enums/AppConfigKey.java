package com.norra.enums;

public enum AppConfigKey {

	BROAD_THRESHOLD("BROAD_THRESHOLD"), // in meters
	WALKING_THRESHOLD("WALKING_THRESHOLD"), // in meters
	GUEST_ROUTE_MIN_MATCH_PERCENT("GUEST_ROUTE_MIN_MATCH_PERCENT"),
	HOST_ROUTE_MIN_MATCH_PERCENT("HOST_ROUTE_MIN_MATCH_PERCENT"),
	USER_PREF_MIN_MATCH_PERCENT("USER_PREF_MIN_MATCH_PERCENT"),
	SCHEDULE_RIDE_DAYS_GAP("SCHEDULE_RIDE_DAYS_GAP"), // days
	SCHEDULE_RIDE_TIME_GAP("SCHEDULE_RIDE_TIME_GAP"), // mins
	HOST_RIDER_TIME_WINDOW_OFFSET_IN_MINUTES("HOST_RIDER_TIME_WINDOW_OFFSET_IN_MINUTES"), //mins
	GUEST_RIDER_TIME_WINDOW_OFFSET_IN_MINUTES("GUEST_RIDER_TIME_WINDOW_OFFSET_IN_MINUTES"), //mins
	SCHEDULE_RIDE_START_OFFSET("SCHEDULE_RIDE_START_OFFSET"), // in mins
	SCHEDULE_RIDE_END_OFFSET("SCHEDULE_RIDE_END_OFFSET"), // mins
	MINUMUM_POOLING_DISTANCE("MINUMUM_POOLING_DISTANCE"), // meters
	OFFER_RIDE_LIMIT_PER_DAY("OFFER_RIDE_LIMIT_PER_DAY"), // number of rides
	USER_CACHE_TTL("USER_CACHE_TTL"), // miliseconds
	ACTIVATION_WINDOW("ACTIVATION_WINDOW"), // mins
	GUEST_AFFINITY_SCORE_WEIGHTAGE("GUEST_AFFINITY_SCORE_WEIGHTAGE"),
	GUEST_ROUTE_MATCH_WEIGHTAGE("GUEST_ROUTE_MATCH_WEIGHTAGE"),
	GUEST_RATING_WEIGHTAGE("GUEST_RATING_WEIGHTAGE"),
	HOST_AFFINITY_SCORE_WEIGHTAGE("HOST_AFFINITY_SCORE_WEIGHTAGE"),
	HOST_ROUTE_MATCH_WEIGHTAGE("HOST_ROUTE_MATCH_WEIGHTAGE"),
	HOST_RATING_WEIGHTAGE("HOST_RATING_WEIGHTAGE"),
	HOST_FARE_WEIGHTAGE("HOST_FARE_WEIGHTAGE"),
	DEFAULT_RATING("DEFAULT_RATING"),
	TRIP_AUTO_CHECKIN_BUFFER_MILLIS("TRIP_AUTO_CHECKIN_BUFFER_MILLIS"),
	TRIP_AUTO_CHECKOUT_BUFFER_MILLIS("TRIP_AUTO_CHECKOUT_BUFFER_MILLIS"),
	TRIP_AUTO_EXPIRE_MILLIS("TRIP_AUTO_EXPIRE_MILLIS"),
	RIDE_AUTO_EXPIRE_MILLIS("RIDE_AUTO_EXPIRE_MILLIS"),
	END_RIDE_MIN_DISTANCE("END_RIDE_MIN_DISTANCE"),
	START_TRIP_TIME_OFFSET("START_TRIP_TIME_OFFSET"),
	TRIP_END_TIME_BUFFER_MIILIS("TRIP_END_TIME_BUFFER_MIILIS"),
	REQ_INV_LIMIT_PER_USER("REQ_INV_LIMIT_PER_USER"),
	VEHICLE_FUEL_TYPE_REQUIRED("VEHICLE_FUEL_TYPE_REQUIRED"),
	VEHICLE_YEAR_REQUIRED("VEHICLE_YEAR_REQUIRED"),
	VEHICLE_REG_NO_REQUIRED("VEHICLE_REG_NO_REQUIRED"),
	VEHICLE_CATEGORY_REQUIRED("VEHICLE_CATEGORY_REQUIRED"),
	VEHICLE_BRAND_REQUIRED("VEHICLE_BRAND_REQUIRED"),
	VEHICLE_MODEL_REQUIRED("VEHICLE_MODEL_REQUIRED"),
	VEHICLE_COLOR_REQUIRED("VEHICLE_COLOR_REQUIRED"),
	VEHICLE_DL_INPUT_REQUIRED("VEHICLE_DL_INPUT_REQUIRED"),
	ROUTE_MATCH_UPPER_CAP("ROUTE_MATCH_UPPER_CAP"),
	PRE_RIDE_START_TIME_REMINDER("PRE_RIDE_START_TIME_REMINDER"),
	POST_RIDE_START_TIME_REMINDER_FIRST("POST_RIDE_START_TIME_REMINDER_FIRST"),
	POST_RIDE_START_TIME_REMINDER_SECOND("POST_RIDE_START_TIME_REMINDER_SECOND"),
	CLEAR_DUES_PN_TIME("CLEAR_DUES_PN_TIME"),
	RIDE_AUTO_CANCEL_MILLIS_FOR_DUES("RIDE_AUTO_CANCEL_MILLIS_FOR_DUES"), //cancel time for ride with dues
	CUSTOMER_SUPPORT_NUMBER("CUSTOMER_SUPPORT_NUMBER"), //string
	ASSISTANCE_NUMBER("ASSISTANCE_NUMBER"), //string
	MINIMUM_OVERLAP_DISTANCE("MINIMUM_OVERLAP_DISTANCE"), //meters
	MIN_TRIP_START_TIME_WITH_PENALTY("MIN_TRIP_START_TIME_WITH_PENALTY"), //minutes
	MIN_TRIP_START_TIME("MIN_TRIP_START_TIME"), // minutes
	MIN_TRIP_START_DISTANCE("MIN_TRIP_START_DISTANCE"), // meters
	DEFAULT_TIME_ADDITION("DEFAULT_TIME_ADDITION"),  // miliseconds
	MAXSWIPES_ALLOWED("MAXSWIPES_ALLOWED"),
	ROUND_TIME_TO("ROUND_TIME_TO"), // minutes
	GOOGLE_API_KEY("GOOGLE_API_KEY"), //string
	DISABLE_LEFT_SWIPE("DISABLE_LEFT_SWIPE"), //string
	DISABLE_RIGHT_SWIPE("DISABLE_RIGHT_SWIPE"), // string
	USE_JEDIS("USE_JEDIS"), //String. Needs to be converted to boolean
	FROM_REDISEARCH("FROM_REDISEARCH"),
	MINIMUM_DISTANCE_GUEST_NEIGHBORING_GEOHASHES("MINIMUM_DISTANCE_GUEST_NEIGHBORING_GEOHASHES"),
	GEOHASH_PRECISION("GEOHASH_PRECISION"),
	MIN_TRIP_CHECKIN_DISTANCE("MIN_TRIP_CHECKIN_DISTANCE"),
	MIN_TRIP_CHECKOUT_DISTANCE("MIN_TRIP_CHECKOUT_DISTANCE"),
	SENDBIRD_APP_ID("SENDBIRD_APP_ID"),
	MIN_TRIP_END_DISTANCE("MIN_TRIP_END_DISTANCE"),
	RE_TRIGGER_MILLIS("RE_TRIGGER_MILLIS"), // millis
	RE_VIEW_MAX_COUNT("RE_VIEW_MAX_COUNT"),
	RE_CACHE_TTL_MINS("RE_CACHE_TTL_MINS"); //minutes

	private final String value;

	private AppConfigKey(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public static AppConfigKey getEnum(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		for (AppConfigKey v : values()) {
			if (value.equalsIgnoreCase(v.getValue())) {
				return v;
			}
		}
		throw new IllegalArgumentException();
	}

}
