package br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces;

import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.BookRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponse;

import java.util.List;
import java.util.Optional;

public interface BookUseCase {
    BookResponse saveBook(BookRequest request);
    List<BookResponse> listAllBooks();
    Optional<BookResponse> findBookById(Long id);
    BookResponse updateBook(Long id, BookRequest request);
    void deleteBook(Long id);
}
