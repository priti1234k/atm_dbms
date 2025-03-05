package com.bank.atm.models;

public enum UserType {

    ADMIN("ADMIN"),
    USER("USER");

    private final String userType;

    UserType(String userType) {
        this.userType = userType;
    }

    public String getValue() {
        return this.userType;
    }
}
