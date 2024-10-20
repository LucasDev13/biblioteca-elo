package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record UserRequest(
        @NotBlank(message = "Não pode ser em branco e null!")
        @NotEmpty(message = "Não pode estar vazio!")
        String name,
        @NotBlank(message = "Não pode ser em branco e null!")
        @NotEmpty(message = "Não pode estar vazio!")
        @Email
        String email,
        @NotBlank(message = "Não pode ser em branco e null!")
        @NotEmpty(message = "Não pode estar vazio!")
        @Pattern(regexp = "\\d{12}", message = "O campo deve conter exatamente 12 dígitos")
        String phoneNumber) {
}
