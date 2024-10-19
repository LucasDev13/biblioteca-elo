package br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.BookRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponse;

import java.util.List;
import java.util.UUID;

public interface BookUseCase {

    BookResponse saveBook(BookRequest request);
    List<BookResponse> listAllBooks();
    BookResponse findById(UUID id);
    Book findBookById(UUID id);
    BookResponse updateBook(UUID id, BookRequest request);
    void deleteBook(UUID id);
}
