package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record BookRequest(
        @NotBlank(message = "Não pode ser em branco e null!")
        @NotEmpty(message = "Não pode estar vazio!")
        String title,

        @NotBlank(message = "Não pode ser em branco e null!")
        @NotEmpty(message = "Não pode estar vazio!")
        String author,

        @NotBlank(message = "Não pode ser em branco e null!")
        @NotEmpty(message = "Não pode estar vazio!")
        String isbn,

        @NotBlank(message = "Não pode ser em branco e null!")
        @NotEmpty(message = "Não pode estar vazio!")
        String category,

        @PastOrPresent(message = "A data precisa estar no passado ou no presente")
        LocalDate publicationDate) {
}
