package br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers;

import br.com.elotech.biblioteca_elo.domain.entities.BookDomain;
import br.com.elotech.biblioteca_elo.domain.entities.LoanDomain;
import br.com.elotech.biblioteca_elo.domain.entities.UserDomain;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Loan;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.User;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.BookRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.UserRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponse;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.LoanResponse;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MappingLayerObjectsImpl implements MappingLayerObjects {

    /*
     * User Mapper
     */
    @Override
    public UserDomain fromRequestToDomain(UserRequest request) {
        if (!Objects.isNull(request)) {
            return UserDomain.builder()
                    .name(request.name())
                    .email(request.email())
                    .phoneNumber(request.phoneNumber())
                    .build();
        }
        throw new IllegalArgumentException("Request cannot be null");
    }

    @Override
    public User fromDomainToEntity(UserDomain domain) {
        if (!Objects.isNull(domain)) {
            return User.builder()
                    .name(domain.getName())
                    .email(domain.getEmail())
                    .phoneNumber(domain.getPhoneNumber())
                    .build();
        }
        throw new IllegalArgumentException("Domain object cannot be null");
    }

    @Override
    public UserDomain fromEntityToDomain(User entity) {
        if (!Objects.isNull(entity)) {
            return UserDomain.builder()
                    .name(entity.getName())
                    .email(entity.getEmail())
                    .phoneNumber(entity.getPhoneNumber())
                    .build();
        }
        throw new IllegalArgumentException("Entity object cannot be null");
    }

    @Override
    public UserResponse fromDomainToResponse(UserDomain domain) {
        if (!Objects.isNull(domain)) {
            return new UserResponse(
                    domain.getName(),
                    domain.getEmail(),
                    domain.getPhoneNumber()
            );
        }
        throw new IllegalArgumentException("Domain object cannot be null");
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
                    .category(domain.getCategory())
                    .publicationDate(domain.getPublicationDate())
                    .registrationDate(domain.getRegistrationDate())
                    .build();
        }
        throw new IllegalArgumentException("Domain object cannot be null");
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
        throw new IllegalArgumentException("Entity object cannot be null");
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
        throw new IllegalArgumentException("Domain object cannot be null");
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
        throw new IllegalArgumentException("Entity object cannot be null");
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
        throw new IllegalArgumentException("Domain object cannot be null");
    }



}
