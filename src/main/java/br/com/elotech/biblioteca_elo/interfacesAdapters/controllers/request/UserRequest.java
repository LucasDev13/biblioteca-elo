package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record UserRequest(
        @NotEmpty(message = "Não pode estar vazio ou null!")
        String name,

        @NotEmpty(message = "Não pode estar vazio ou null!")
        @Email
        String email,

        @NotEmpty(message = "Não pode estar vazio ou null!")
        @Pattern(regexp = "\\d{12}", message = "O campo deve conter exatamente 12 dígitos")
        String phoneNumber) {
}
