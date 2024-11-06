package br.com.elotech.biblioteca_elo.application.usecases;

import br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers.MappingLayerObjects;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.bookRepository.BookRepository;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.loanRepository.LoanRepository;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookRecommendationUseCase {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MappingLayerObjects mapper;

    public BookRecommendationUseCase(LoanRepository loanRepository, BookRepository bookRepository, MappingLayerObjects mapper) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }


    public List<BookResponse> recommendBooks(UUID userId) {
        List<UUID> categories = getCategories(userId);
        List<Book> recommendBooks = getRecommendBooks(userId, categories);
        return mapper.listFromDomainToResponse(mapper.listFromEntityToDomain(recommendBooks));
    }

    private List<Book>  getRecommendBooks(UUID userId, List<UUID> categories) {
        return bookRepository.findBooksByCategoryAndNotBorrowedByUser(categories, userId);
    }

    private List<UUID> getCategories(UUID userId) {
        return loanRepository.findDistinctCategoriesByUserId(userId);
    }

}
