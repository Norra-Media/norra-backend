package com.norra.enums;

public enum Days {

	NONE("NONE"), MONDAY("Monday"), TUESDAY("Tuesday"), WEDNESDAY("Wednesday"), THURSDAY("Thursday"), FRIDAY("Friday"),
	SATURDAY("Saturday"), SUNDAY("Sunday");

	private final String value;

	private Days(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public static Days getEnum(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		for (Days v : values()) {
			if (value.equalsIgnoreCase(v.getValue())) {
				return v;
			}
		}
		throw new IllegalArgumentException();
	}

}
