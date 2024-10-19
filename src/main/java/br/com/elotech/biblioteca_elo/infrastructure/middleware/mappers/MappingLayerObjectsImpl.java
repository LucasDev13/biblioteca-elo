package br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers;

import br.com.elotech.biblioteca_elo.domain.entities.BookDomain;
import br.com.elotech.biblioteca_elo.domain.entities.UserDomain;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.User;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.BookRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.UserRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponse;
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
        throw new IllegalArgumentException("Domain object cannot be null");
    }

    @Override
    public BookDomain fromEntityToDomain(Book entiy) {
        if (!Objects.isNull(entiy)) {
            return BookDomain
                    .builder()
                    .title(entiy.getTitle())
                    .author(entiy.getAuthor())
                    .isbn(entiy.getIsbn())
                    .publicationDate(entiy.getPublicationDate())
                    .registrationDate(entiy.getRegistrationDate())
                    .build();
        }
        throw new IllegalArgumentException("Entity object cannot be null");
    }

    @Override
    public BookResponse fromDomainToResponse(BookDomain domain) {
        if (!Objects.isNull(domain)) {
            return new BookResponse(
                    domain.getTitle(),
                    domain.getAuthor(),
                    domain.getIsbn(),
                    domain.getPublicationDate(),
                    domain.getRegistrationDate()
            );
        }
        throw new IllegalArgumentException("Domain object cannot be null");
    }
}
