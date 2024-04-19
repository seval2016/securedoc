package com.getarrayz.securedoc.enumaration;

public enum Authority {

    USER(""),
    ADMIN(""),
    SUPER_ADMIN(""),
    MANAGER("");

    private final String value;

    Authority(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
