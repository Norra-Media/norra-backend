package com.norra.enums;

public enum UserType {

	USER("USER"), 
	ADMIN("ADMIN");

	private final String value;

	private UserType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public static UserType getEnum(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		for (UserType v : values()) {
			if (value.equalsIgnoreCase(v.getValue())) {
				return v;
			}
		}
		throw new IllegalArgumentException();
	}

}
