package br.com.elotech.biblioteca_elo.application.usecases;

import br.com.elotech.biblioteca_elo.domain.entities.BookDomain;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces.BookUseCase;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers.MappingLayerObjects;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.bookRepository.BookRepository;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.BookRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookUseCaseImpl implements BookUseCase {

    private final MappingLayerObjects mapper;
    private final BookRepository repository;

    @Autowired
    public BookUseCaseImpl(MappingLayerObjects mapper, BookRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public BookResponse saveBook(BookRequest request) {
        BookDomain domain = mapper.fromRequestToDomain(request, BookDomain.class);
        Book bookSaved = repository.save(mapper.fromDomainToEntity(domain, Book.class));
        return mapper.fromDomainToResponse(mapper.fromEntityToDomain(bookSaved, BookDomain.class), BookResponse.class);
    }

    @Override
    public List<BookResponse> listAllBooks() {
        return List.of();
    }

    @Override
    public Optional<BookResponse> findBookById(Long id) {
        return Optional.empty();
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest request) {
        return null;
    }

    @Override
    public void deleteBook(Long id) {

    }
}
