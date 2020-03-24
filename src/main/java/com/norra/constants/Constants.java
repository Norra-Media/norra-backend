package com.norra.constants;

import java.text.SimpleDateFormat;

public class Constants {

    private Constants() {
    }

	public static final SimpleDateFormat SCHEDULE_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
	public static final SimpleDateFormat DAY_WITHOUT_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final String SIMPLE_TIME_FORMAT = "yyyy-MM-dd";
	public static final String DECIMAL_FORMAT_PRECISION_ZERO = "#";
	public static final String DECIMAL_FORMAT_PRECISION_ONE = "#.#";

    public static final String STATUS_SUCCESS = "Success";
    public static final String STATUS_FAILED = "Failed";

    // for JWT token Authorization
    public static final String BAD_CREDENTIALS_EXCEPTION = "The API key was not found \"\n"
            + "                            + \"or not the expected value";
    public static final String ANT_MATCHER_URL = "/api/**";
    public static final String NOT_AVAILABLE = "N/A";
    public static final String NOT_ALLOWED = "User is does not have enough privileges for this action";
    public static final String AUTH_HEADER_TOKEN = "Authorization";

    // Push Notification
    public static final String MEDIA_TYPE = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String HEADER_TOKEN = "token";
    public static final String DATA = "data";
    public static final String USER_PROFILES = "user_profiles";
    public static final String DND_START_TIME = "22:00";
    public static final String DND_END_TIME = "08:00";


    // tagged user address api constants
    public static final long JWT_TOKEN_EPOCH = 1000;
    public static final long EARTH_RADIUS_NUMBER = 1000;
    public static final String DATA_ADDED_SUCCESSFULLY = "DATA_ADDED_SUCCESSFULLY";
    public static final String TAGGED_USER_ADDRESS_DETAILS = " address details";
    public static final String BAD_REQUEST = "Bad request. Valid data missing";
    public static final String TAG_ALREADY_EXISTS = "Preference is already set";
    public static final String DATA_NOT_FOUND = "Data not found";
    public static final String DATA_NOT_FOUND_IN_CACHE = "Data not found in cache";
    public static final String DATA_DELETED = "DATA_DELETED";
    public static final String UPDATED_SUCCESSFULLY = "UPDATED_SUCCESSFULLY";



    // For magic numbers
    public static final Double PERCENT_HUNDRED = 100.0;
    public static final int IST_TIMEZONE_HOUR = 5;
    public static final int IST_TIMEZONE_MIN = 30;
    public static final int METER_TO_KM = 1000;
    public static final int PRECISION_SIX = 6;
    public static final int PRECISION_NINE = 9;
    public static final int PRECISION_TWELVE = 12;
    public static final int TIME_MINUTES = 60;
    public static final int TIME_HOURS = 24;
    public static final int TIME_SECONDS = 60;
    public static final int FIVE_HOUR_THIRTY_MINS_IN_SECONDS = 19800;
    public static final int MILLISECONDS_TO_SECONDS = 1000;


    // Generic
    public static final String COMMA = ",";
    public static final String COLON_SPACE = ": ";
    public static final String SPACE = " ";

    // user config constants
    public static final String USER_CONFIG_DETAILS = "User Config details";
    public static final String STATUS = "status";
    public static final String USER_CONFIG_DETAILS_NOT_FOUND = "User config details not found.";
    public static final String RIDE_TAKEN_OFFERED = "number of rides taken/offered";
    public static final String SERVER_ERROR = "Server Error";
    public static final String USER_IDS = "user_ids";
    public static final String USER_SUMMARY = "User Summary details";
    public static final String USER_SUMMARY_NOT_FOUND = "User Summary details not found";
    public static final String USER_ID_REQUIRED = "User Id Required";

    public static final String USER_RATING_UPDATED = "User rating updated";
    public static final String USER_DETAILS = "User details";
    public static final String USER_DETAILS_NOT_FOUND = "User details not found";
    public static final String APP_INIT_INFO = "App init info";

    // App config
    public static final String APP_CONFIG_DETAILS = "App config details";
    public static final String APP_CONFIG_RIDE_PREFERENCES = "RIDE_PREFERENCES";
    public static final String RIDE_CONFIG_VALUE_ZERO = "0.0";

    //Auto trip actions
    public static final int DEFAULT_TRIP_AUTO_CHECKIN_BUFFER_MILLIS = 15 * 60 * 1000;
    public static final int DEFAULT_TRIP_AUTO_CHECKOUT_BUFFER_MILLIS = 30 * 60 * 1000;
    public static final int DEFAULT_TRIP_AUTO_EXPIRE_MILLIS = 30 * 60 * 1000;
    public static final int DEFAULT_RIDE_AUTO_EXPIRE_MILLIS = 30 * 60 * 1000;
    public static final int DEFAULT_RIDE_CLEAR_DUES_PN_MILLIS = 60 * 60 * 1000;
    public static final int DEFAULT_RIDE_AUTO_CANCEL_MILLIS_FOR_DUES = 30 * 60 * 1000;

    // App info api
    public static final String APP_INFO_REQUEST_NOT_NULL = "App info request cannot be null";
    public static final String INFO_TYPE_MANDATORY = "infoType cannot be null or empty";
    public static final String KEY_AND_INFO_ALREADY_PRESENT = "Key and info type combination is already present";
    public static final String KEY_MANDATORY = "key cannot be null";
    public static final String APP_INFO_NOT_FOUND = "App Info not found";
    public static final String APP_INFO_LIST = "App Info list";
    public static final String INFO_TYPE_LIST_NULL_EMPTY = "infoType list is null or empty";
    public static final String OTHERS_APP_INFO_KEY_INFO = "CANCEL_REASON_OTHERS";
    public static final String CANCEL = "CANCEL";

    //Push notification types
    public static final String IN_TRIP = "IN_TRIP";
    public static final String AUTO_IN_TRIP = "AUTO_IN_TRIP";
    public static final String COMPLETED = "COMPLETED";
    public static final String AUTO_COMPLETED = "AUTO_COMPLETED";


    //JDBC Errors
    public static final String CONSTRAINT_VIOLATION = "Constraint violation";
    public static final String CONSTRAINT_COLUMN = "Column validation failed";
    public static final String CONSTRAINT_VIOLATION_MSG = "CONSTRAINT_VIOLATION_MSG";
    // App init info
    public static final String NOTIF_DATE_FORMAT = "dd MMM, yy";
    public static final String NOTIF_TIME_FORMAT = "hh:mm a";
    public static final String NOTIF_DATE_TIME_FORMAT = " dd MMM, yy hh:mm a";
    
    public static final String TIMEZONE_UTC = "UTC";
    public static final String TIMEZONE_IST = "Asia/Kolkata";
    





 // Databases
    public static final String DB_READ_REPLICA_NAME = "readreplica";


}
