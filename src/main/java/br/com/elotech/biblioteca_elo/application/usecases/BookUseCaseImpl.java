package br.com.elotech.biblioteca_elo.application.usecases;

import br.com.elotech.biblioteca_elo.domain.entities.BookDomain;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces.BookUseCase;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers.MappingLayerObjects;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.bookRepository.BookRepository;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.BookRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class BookUseCaseImpl implements BookUseCase {

    private final MappingLayerObjects mapper;
    private final BookRepository repository;

    public BookUseCaseImpl(MappingLayerObjects mapper, BookRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public BookResponse saveBook(BookRequest request) {
        BookDomain domain = mapper.fromRequestToDomain(request);
        domain.setRegistrationDate(LocalDateTime.now());
        Book bookSaved = repository.save(mapper.fromDomainToEntity(domain));
        return mapper.fromDomainToResponse(mapper.fromEntityToDomain(bookSaved));
    }

    @Override
    public List<BookResponse> listAllBooks() {
        List<Book> allBooks = repository.findAll();
        return allBooks
                .stream()
                .map(book -> mapper.fromDomainToResponse(mapper.fromEntityToDomain(book)))
                .toList();
    }

    @Override
    public BookResponse findById(UUID id) {
        Book book = findBookById(id);
        return mapper.fromDomainToResponse(mapper.fromEntityToDomain(book));
    }

    @Override
    public BookResponse updateBook(UUID id, BookRequest request) {
        Book book = findBookById(id);
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setIsbn(request.isbn());
        book.setPublicationDate(request.publicationDate());

        repository.save(book);
        return mapper.fromDomainToResponse(mapper.fromEntityToDomain(book));
    }

    @Override
    public void deleteBook(UUID id) {
        Book book = findBookById(id);
        if(!Objects.isNull(book)){
            repository.delete(book);
        }
    }

    @Override
    public Book findBookById(UUID id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book id not found" + id));
    }
}
