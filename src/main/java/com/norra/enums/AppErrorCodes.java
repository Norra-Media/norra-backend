package com.norra.enums;

public enum AppErrorCodes {

    E100("E100"),  
    E200("E200"),   // This code is thrown when there is app specific validation failure. This validation message will be shown to the users
	E400("E400"),   // This code is thrown when there is request input parsing failure. This validation message will not be shown to the user and  something went wrong will be displayed
    E500("E500"),
	E401("E401");  // When this code will be received by frontend, error message sent by backend will be displayed and screen refresh will happen.

    private final String value;

    private AppErrorCodes(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public static AppErrorCodes getEnum(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        for (AppErrorCodes v : values()) {
            if (value.equalsIgnoreCase(v.getValue())) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
