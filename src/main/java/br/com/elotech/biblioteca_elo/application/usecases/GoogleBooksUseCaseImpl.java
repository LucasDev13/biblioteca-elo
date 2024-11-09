package br.com.elotech.biblioteca_elo.application.usecases;

import br.com.elotech.biblioteca_elo.infrastructure.middleware.gateways.GoogleBooksClient;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces.GoogleBooksUseCase;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoogleBooksUseCaseImpl implements GoogleBooksUseCase {

    private final GoogleBooksClient googleBooksClient;

    @Autowired
    public GoogleBooksUseCaseImpl(GoogleBooksClient googleBooksClient) {
        this.googleBooksClient = googleBooksClient;
    }


    @Override
    public List<String> searchBookTitle(String query) {
        BookResponseClient response = googleBooksClient.searchBooks(query);
        var items =  response.items();
        return items.stream()
                .map(item -> item.volumeInfo().title())
                .toList();
    }
}
