package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers;

import br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces.BookUseCase;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.BookRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookUseCase bookUseCase;

    @Autowired
    public BookController(BookUseCase bookUseCase) {
        this.bookUseCase = bookUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> createBook(@Valid @RequestBody BookRequest request) {
        BookResponse newBook = bookUseCase.saveBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> listBooks() {
        List<BookResponse> books = bookUseCase.listAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@Valid @PathVariable Long id) {
        Optional<BookResponse> book = bookUseCase.findBookById(id);
        return book.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@Valid @PathVariable Long id, @RequestBody BookRequest request) {
        BookResponse updatedBook = bookUseCase.updateBook(id, request);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@Valid @PathVariable Long id) {
        bookUseCase.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}