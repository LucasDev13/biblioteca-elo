package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response;

public record UserResponse(
        String name,
        String email,
        String phoneNumber) {
}
