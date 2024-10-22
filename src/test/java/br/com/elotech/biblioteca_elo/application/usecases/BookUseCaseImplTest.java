package br.com.elotech.biblioteca_elo.application.usecases;

import br.com.elotech.biblioteca_elo.domain.entities.BookDomain;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers.MappingLayerObjects;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Category;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.bookRepository.BookRepository;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.BookRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookUseCaseImplTest {

    @Mock
    private MappingLayerObjects mapper;

    @Mock
    private BookRepository repository;

    private BookUseCaseImpl bookUseCase;

    @BeforeEach
    void setUp() {
        bookUseCase = new BookUseCaseImpl(mapper, repository);
    }

    @Test
    void testSaveBook_shouldSaveBookAndReturnResponse() {
        BookRequest request = new BookRequest(
                "Title",
                "Author",
                "123456789",
                new Category(UUID.randomUUID(), "History"),
                LocalDateTime.now().toLocalDate());
        BookDomain domain = BookDomain.builder()
                .id(UUID.fromString("1099decd-6780-4ca1-b396-805ea08455d1"))
                .title("Title")
                .author("Author")
                .isbn("0987654321")
                .category(new Category(UUID.fromString("0101afe8-73e8-4409-955f-b76d5e91a382"), "History"))
                .publicationDate(LocalDate.now())
                .registrationDate(LocalDateTime.now())
                .build(); // Set domain values here
        Book book = new Book();
        BookResponse response = new BookResponse(
                UUID.randomUUID(),
                "Title",
                "Author",
                "1234567890",
                new Category(UUID.randomUUID(), "History"),
                LocalDate.now(),
                LocalDateTime.now());

        Mockito.when(mapper.fromRequestToDomain(request)).thenReturn(domain);
        Mockito.when(mapper.fromDomainToEntity(domain)).thenReturn(book);
        Mockito.when(repository.save(book)).thenReturn(book);
        Mockito.when(mapper.fromEntityToDomain(book)).thenReturn(domain);
        Mockito.when(mapper.fromDomainToResponse(domain)).thenReturn(response);

        BookResponse actualResponse = bookUseCase.saveBook(request);

        Mockito.verify(mapper, Mockito.times(1)).fromRequestToDomain(request);
        Mockito.verify(mapper, Mockito.times(1)).fromDomainToEntity(domain);
        Mockito.verify(repository, Mockito.times(1)).save(book);
        Mockito.verify(mapper, Mockito.times(1)).fromEntityToDomain(book);
        Mockito.verify(mapper, Mockito.times(1)).fromDomainToResponse(domain);
    }

    @Test
    void testListAllBooks_shouldReturnListOfBookResponses() {
        List<Book> books = new ArrayList<>();
        List<BookResponse> expectedResponses = books
                .stream()
                .map(book -> mapper.fromDomainToResponse(mapper.fromEntityToDomain(book)))
                .toList();

        Mockito.when(repository.findAll()).thenReturn(books);

        List<BookResponse> actualResponses = bookUseCase.listAllBooks();

        Mockito.verify(repository, Mockito.times(1)).findAll();

        assertEquals(expectedResponses, actualResponses);
    }

    @Test
    void testFindBookById_shouldReturnBook() {
        Book book = new Book(
                UUID.fromString("1099decd-6780-4ca1-b396-805ea08455d1"),
                "Title", "Author", "ISBN", new Category(UUID.randomUUID(), "History"),
                LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());

        when(repository.findById(any())).thenReturn(Optional.of(book));

        Book response = bookUseCase.findBookById(UUID.fromString("4795cdcc-ac2f-44f3-86a3-628796b319ac"));

        assertNotNull(response);
        verify(repository, times(1)).findById(UUID.fromString("4795cdcc-ac2f-44f3-86a3-628796b319ac"));
    }

    @Test
    void testUpdateBook_Success() {
        UUID bookId = UUID.fromString("1099decd-6780-4ca1-b396-805ea08455d1");
        BookRequest bookRequest = new BookRequest("New Title","New Author","1234567890", new Category(UUID.randomUUID(), "Science"),LocalDate.now());
        Book book = new Book(bookId,"Title", "Author", "ISBN", new Category(UUID.randomUUID(), "History"),
                LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());

        BookResponse bookResponse = new BookResponse(UUID.fromString("1099decd-6780-4ca1-b396-805ea08455d1"), "New Title","New Author", "1234567890",
                new Category(UUID.randomUUID(), "Science"), LocalDate.now(), LocalDateTime.now());

        when(repository.findById(UUID.fromString("1099decd-6780-4ca1-b396-805ea08455d1"))).thenReturn(Optional.of(book));

        when(mapper.fromEntityToDomain(book)).thenReturn(BookDomain.builder().build());
        when(mapper.fromDomainToResponse(any(BookDomain.class))).thenReturn(bookResponse);

        BookResponse response = bookUseCase.updateBook(bookId, bookRequest);

        assertNotNull(response);
        assertEquals(bookRequest.title(), response.title());
        assertEquals(bookRequest.author(), response.author());
        assertEquals(bookRequest.isbn(), response.isbn());

        verify(repository, times(1)).save(book);
        verify(mapper, times(1)).fromDomainToResponse(any(BookDomain.class));
    }

    @Test
    void testDeleteBook_Success() {
        Book book = new Book(
                UUID.fromString("1099decd-6780-4ca1-b396-805ea08455d1"),
                "Title", "Author", "ISBN", new Category(UUID.randomUUID(), "History"),
                LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        when(repository.findById(any())).thenReturn(Optional.of(book));

        bookUseCase.deleteBook(UUID.fromString("1099decd-6780-4ca1-b396-805ea08455d1"));

        verify(repository, times(1)).delete(book);
    }
}