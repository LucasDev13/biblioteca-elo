package br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers;

import br.com.elotech.biblioteca_elo.domain.entities.BookDomain;
import br.com.elotech.biblioteca_elo.domain.entities.LoanDomain;
import br.com.elotech.biblioteca_elo.domain.entities.UserDomain;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Contact;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Loan;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Person;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.User;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.BookRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.UserRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponse;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.LoanResponse;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class MappingLayerObjectsImpl implements MappingLayerObjects {

    private static final String OBJECT_DOMAIN_NOT_NULL = "Domain object cannot be null";
    private static final String OBJECT_ENTITY_NOT_NULL = "Entity object cannot be null";

    /*
     * User Mapper
     */
    @Override
    public UserDomain fromRequestToDomain(UserRequest request) {
        var contact = new Contact(request.email(), request.phoneNumber());
        var userDomaion = UserDomain.builder()
                .person(Person.builder()
                        .name(request.name())
                        .contact(List.of(contact))
                        .build())
                .build();
        log.info("--Método fromRequestToDomain--");
        log.info("Camada de request para domain: {}", userDomaion.toString());
        return userDomaion;
    }

    @Override
    public User fromDomainToEntity(UserDomain domain) {
        if (!Objects.isNull(domain)) {
            var user = User.builder().person(domain.getPerson()).build();
            log.info("Objeto user: {}", user.toString());
            return user;
        }
        throw new IllegalArgumentException(OBJECT_DOMAIN_NOT_NULL);
    }

    @Override
    public UserDomain fromEntityToDomain(User entity) {
        if (!Objects.isNull(entity)) {
            var userDomain = UserDomain.builder()
                    .person(entity.getPerson())
                    .build();
            log.info("--Método fromEntityToDomain--");
            log.info("Camada de entity para domain: {}", userDomain.toString());
            return userDomain;
        }
        throw new IllegalArgumentException(OBJECT_ENTITY_NOT_NULL);
    }

    @Override
    public UserResponse fromDomainToResponse(UserDomain domain) {
        if (!Objects.isNull(domain)) {
            var userResponse = new UserResponse(
                    domain.getPerson().getName(),
                    domain.getPerson().getContact().stream().map(Contact::getEmail).toString(),
                    domain.getPerson().getContact().stream().map(Contact::getPhoneNumber).toString()
            );
            log.info("Objeto user response: {}", userResponse.toString());
            return userResponse;
        }
        throw new IllegalArgumentException(OBJECT_DOMAIN_NOT_NULL);
    }

    /*
     * Book Mapper
     */
    @Override
    public BookDomain fromRequestToDomain(BookRequest request) {
        if (!Objects.isNull(request)) {
            return BookDomain
                    .builder()
                    .title(request.title())
                    .author(request.author())
                    .isbn(request.isbn())
                    .category(request.category())
                    .publicationDate(request.publicationDate())
                    .build();
        }
        throw new IllegalArgumentException("Request cannot be null");
    }

    @Override
    public Book fromDomainToEntity(BookDomain domain) {
        if (!Objects.isNull(domain)) {
            return Book
                    .builder()
                    .title(domain.getTitle())
                    .author(domain.getAuthor())
                    .isbn(domain.getIsbn())
                    .publicationDate(domain.getPublicationDate())
                    .registrationDate(domain.getRegistrationDate())
                    .build();
        }
        throw new IllegalArgumentException(OBJECT_DOMAIN_NOT_NULL);
    }

    @Override
    public BookDomain fromEntityToDomain(Book entity) {
        if (!Objects.isNull(entity)) {
            return BookDomain
                    .builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .author(entity.getAuthor())
                    .isbn(entity.getIsbn())
                    .category(entity.getCategory())
                    .publicationDate(entity.getPublicationDate())
                    .registrationDate(entity.getRegistrationDate())
                    .build();
        }
        throw new IllegalArgumentException(OBJECT_ENTITY_NOT_NULL);
    }

    @Override
    public BookResponse fromDomainToResponse(BookDomain domain) {
        if (!Objects.isNull(domain)) {
            return new BookResponse(
                    domain.getId(),
                    domain.getTitle(),
                    domain.getAuthor(),
                    domain.getIsbn(),
                    domain.getCategory(),
                    domain.getPublicationDate(),
                    domain.getRegistrationDate()
            );
        }
        throw new IllegalArgumentException(OBJECT_DOMAIN_NOT_NULL);
    }

    /*
     * Loan Mapper
     */
    @Override
    public LoanDomain fromEntityToDomain(Loan entiy) {
        if (!Objects.isNull(entiy)) {
            return LoanDomain
                    .builder()
                    .userId(entiy.getUser().getId())
                    .bookId(entiy.getBook().getId())
                    .loanDate(entiy.getLoanDate())
                    .expectedReturnDateBook(entiy.getExpectedReturnDateBook())
                    .status(entiy.getStatus())
                    .build();
        }
        throw new IllegalArgumentException(OBJECT_ENTITY_NOT_NULL);
    }

    @Override
    public LoanResponse fromDomainToResponse(LoanDomain domain) {
        if (!Objects.isNull(domain)) {
            return new LoanResponse(
                    domain.getUserId(),
                    domain.getBookId(),
                    domain.getLoanDate(),
                    domain.getExpectedReturnDateBook(),
                    domain.getStatus()
            );
        }
        throw new IllegalArgumentException(OBJECT_DOMAIN_NOT_NULL);
    }

    @Override
    public List<BookDomain> listFromEntityToDomain(List<Book> books) {
        return books.stream()
                .map(book -> BookDomain.builder()
                        .id(book.getId())
                        .title(book.getTitle() != null ? book.getTitle() : "Unknown Title")
                        .author(book.getAuthor() != null ? book.getAuthor() : "Unknown Author")
                        .isbn(book.getIsbn() != null ? book.getIsbn() : "Unknown ISBN")
                        .category(book.getCategory())
                        .publicationDate(book.getPublicationDate())
                        .registrationDate(book.getRegistrationDate())
                        .build()
                ).toList();
    }

    @Override
    public List<BookResponse> listFromDomainToResponse(List<BookDomain> books) {
        return books.stream()
                .map(bookDomain -> new BookResponse(
                        bookDomain.getId(),
                        bookDomain.getTitle() != null ? bookDomain.getTitle() : "Unknown Title",
                        bookDomain.getAuthor() != null ? bookDomain.getAuthor() : "Unknown Author",
                        bookDomain.getIsbn() != null ? bookDomain.getIsbn() : "Unknown ISBN",
                        bookDomain.getCategory(),
                        bookDomain.getPublicationDate(),
                        bookDomain.getRegistrationDate()
                )).toList();
    }


}
