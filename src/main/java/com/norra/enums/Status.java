package com.norra.enums;

public enum Status {

	TEST("TEST");

	private final String value;

	private Status(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public static Status getEnum(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		for (Status v : values()) {
			if (value.equalsIgnoreCase(v.getValue())) {
				return v;
			}
		}
		throw new IllegalArgumentException();
	}

}
