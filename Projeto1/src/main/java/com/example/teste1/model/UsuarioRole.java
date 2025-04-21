package com.example.teste1.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UsuarioRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

    UsuarioRole(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static UsuarioRole fromValue(String value) {
        for (UsuarioRole role : UsuarioRole.values()) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + value);
    }
}



