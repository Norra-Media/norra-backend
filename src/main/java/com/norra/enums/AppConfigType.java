package com.norra.enums;

public enum AppConfigType {

	RIDE_CONFIGS("RIDE_CONFIGS"),
	SCHEDULED_TASKS_CONFIG("SCHEDULED_TASKS_CONFIG"),
	CACHE_TTL("CACHE_TTL"),
	VEHICLE_CONFIGS("VEHICLE_CONFIGS"),
	PUSH_NOTIFICATION_CONFIG("PUSH_NOTIFICATION_CONFIG"),
	MATCH_CONFIGS("MATCH_CONFIGS"),
	RE_CONFIGS("RE_CONFIGS");


	private final String value;

	private AppConfigType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public static AppConfigType getEnum(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		for (AppConfigType v : values()) {
			if (value.equalsIgnoreCase(v.getValue())) {
				return v;
			}
		}
		throw new IllegalArgumentException();
	}

}
