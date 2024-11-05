package br.com.elotech.biblioteca_elo.application.usecases;

import br.com.elotech.biblioteca_elo.infrastructure.middleware.gateways.GoogleBooksClient;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponseClient;

import java.util.List;

public record GoogleBooksUseCase(
        GoogleBooksClient googleBooksClient
) {

    public List<String> searchBookTitle(String query){
        BookResponseClient response = googleBooksClient.searchBooks(query);
        var items =  response.items();
        return items.stream()
                .map(item -> item.volumeInfo().title())
                .toList();
    }
}
