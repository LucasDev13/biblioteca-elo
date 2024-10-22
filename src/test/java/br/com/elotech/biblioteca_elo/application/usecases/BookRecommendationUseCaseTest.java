package br.com.elotech.biblioteca_elo.application.usecases;

import br.com.elotech.biblioteca_elo.domain.entities.BookDomain;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers.MappingLayerObjects;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Category;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.bookRepository.BookRepository;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.loanRepository.LoanRepository;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookRecommendationUseCaseTest {

    @InjectMocks
    private BookRecommendationUseCase bookRecommendationUseCase;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MappingLayerObjects mapper;

    private UUID userId;
    private List<UUID> categoryIds;
    private List<Book> recommendedBooks;
    private List<BookDomain> bookDomains;
    private List<BookResponse> bookResponses;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        categoryIds = List.of(UUID.randomUUID(), UUID.randomUUID());
        recommendedBooks = List.of(
                new Book(UUID.randomUUID(),
                        "Book 1",
                        "Author 1",
                        "1234567890",
                        new Category(UUID.randomUUID(), "History"),
                        LocalDate.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now()),
                new Book(UUID.randomUUID(),
                        "Book 2",
                        "Author 2",
                        "0987654321",
                        new Category(UUID.randomUUID(), "History"),
                        LocalDate.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now()));

        bookDomains = List.of(
                BookDomain.builder()
                        .id(UUID.randomUUID())
                        .title("Book 1")
                        .author("Author 1")
                        .isbn("1234567890")
                        .category(new Category(UUID.randomUUID(), "History"))
                        .publicationDate(LocalDate.now())
                        .registrationDate(LocalDateTime.now())
                        .build(),
                BookDomain.builder()
                        .id(UUID.randomUUID())
                        .title("Book 2")
                        .author("Author 2")
                        .isbn("0987654321")
                        .category(new Category(UUID.randomUUID(), "History"))
                        .publicationDate(LocalDate.now())
                        .registrationDate(LocalDateTime.now())
                        .build());

        bookResponses = List.of(
                new BookResponse(
                        UUID.randomUUID(),
                        "Book 1",
                        "Author 1",
                        "1234567890",
                        new Category(UUID.randomUUID(), "History"),
                        LocalDate.now(),
                        LocalDateTime.now()),
                new BookResponse(
                        UUID.randomUUID(),
                        "Book 2",
                        "Author 2",
                        "0987654321",
                        new Category(UUID.randomUUID(), "History"),
                        LocalDate.now(),
                        LocalDateTime.now()));
    }

    @Test
    void testRecommendBooksUserHasCategoriesAndBooksAvailable() {
        when(loanRepository.findDistinctCategoriesByUserId(userId)).thenReturn(categoryIds);
        when(bookRepository.findBooksByCategoryAndNotBorrowedByUser(categoryIds, userId)).thenReturn(recommendedBooks);
        when(mapper.listFromEntityToDomain(recommendedBooks)).thenReturn(bookDomains);
        when(mapper.listFromDomainToResponse(bookDomains)).thenReturn(bookResponses);

        List<BookResponse> result = bookRecommendationUseCase.recommendBooks(userId);

        assertEquals(bookResponses, result);
    }

    @Test
    void testRecommendBooksUserHasNoCategories() {
        categoryIds = Collections.emptyList();
        recommendedBooks = Collections.emptyList();
        bookDomains = Collections.emptyList();
        bookResponses = Collections.emptyList();

        when(loanRepository.findDistinctCategoriesByUserId(userId)).thenReturn(categoryIds);
        when(bookRepository.findBooksByCategoryAndNotBorrowedByUser(categoryIds, userId)).thenReturn(recommendedBooks);
        when(mapper.listFromEntityToDomain(recommendedBooks)).thenReturn(bookDomains);
        when(mapper.listFromDomainToResponse(bookDomains)).thenReturn(bookResponses);

        List<BookResponse> result = bookRecommendationUseCase.recommendBooks(userId);

        assertEquals(0, result.size());
    }

    @Test
    void testRecommendBooksUserHasCategoriesButNoBooksAvailable() {
        categoryIds = List.of(UUID.randomUUID(), UUID.randomUUID());
        recommendedBooks = Collections.emptyList();
        bookDomains = Collections.emptyList();
        bookResponses = Collections.emptyList();

        when(loanRepository.findDistinctCategoriesByUserId(userId)).thenReturn(categoryIds);
        when(bookRepository.findBooksByCategoryAndNotBorrowedByUser(categoryIds, userId)).thenReturn(recommendedBooks);
        when(mapper.listFromEntityToDomain(recommendedBooks)).thenReturn(bookDomains);
        when(mapper.listFromDomainToResponse(bookDomains)).thenReturn(bookResponses);

        List<BookResponse> result = bookRecommendationUseCase.recommendBooks(userId);

        assertEquals(0, result.size());
    }
}