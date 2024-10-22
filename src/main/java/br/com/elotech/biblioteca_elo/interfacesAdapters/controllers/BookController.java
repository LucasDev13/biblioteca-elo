package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers;

import br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces.BookUseCase;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.BookRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "API for managing books")
@Validated
public class BookController {

    private final BookUseCase bookUseCase;

    @Autowired
    public BookController(BookUseCase bookUseCase) {
        this.bookUseCase = bookUseCase;
    }

    @Operation(summary = "Create a new book", description = "Creates a new book entry and returns the created book.", tags = {"Books"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created successfully",
                    content = @Content(schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Object> createBook(@Valid @RequestBody BookRequest request) {
        BookResponse newBook = bookUseCase.saveBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @Operation(summary = "List all books", description = "Retrieves a list of all books.", tags = {"Books"})
    @ApiResponse(responseCode = "200", description = "List of books",
            content = @Content(schema = @Schema(implementation = BookResponse.class)))
    @GetMapping
    public ResponseEntity<List<BookResponse>> listBooks() {
        List<BookResponse> books = bookUseCase.listAllBooks();
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Get a book by ID", description = "Retrieves the details of a specific book by its ID.", tags = {"Books"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found",
                    content = @Content(schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@Valid @PathVariable UUID id) {
        if (!Objects.isNull(id)) {
            BookResponse book = bookUseCase.findById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update an existing book", description = "Updates the details of a book by its ID.", tags = {"Books"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully",
                    content = @Content(schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data or book ID",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@Valid @PathVariable UUID id, @RequestBody BookRequest request) {
        BookResponse updatedBook = bookUseCase.updateBook(id, request);
        return ResponseEntity.ok(updatedBook);
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by its ID.", tags = { "Books" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@Valid @PathVariable UUID id) {
        bookUseCase.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
