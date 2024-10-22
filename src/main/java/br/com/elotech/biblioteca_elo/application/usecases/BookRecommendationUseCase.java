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
public record BookRecommendationUseCase(
        LoanRepository loanRepository,
        BookRepository bookRepository,
        MappingLayerObjects mapper
) {

    public List<BookResponse> recommendBooks(UUID userId) {
        List<UUID> categories = loanRepository.findDistinctCategoriesByUserId(userId);

        List<Book> books = bookRepository.findBooksByCategoryAndNotBorrowedByUser(categories, userId);
        //mapper.fromEntityToDomain(books);
        return List.of();
    }

}
