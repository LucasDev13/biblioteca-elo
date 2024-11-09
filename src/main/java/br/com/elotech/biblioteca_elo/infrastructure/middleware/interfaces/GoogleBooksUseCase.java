package br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces;

import java.util.List;

public interface GoogleBooksUseCase {
    List<String> searchBookTitle(String query);
}
