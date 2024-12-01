package br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.enuns;

import lombok.Getter;

@Getter
public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
