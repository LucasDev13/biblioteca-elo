package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request;

import java.time.LocalDate;

public record BookRequest(
        String title,
        String author,
        String isbn,
        LocalDate publicationDate) {
}
