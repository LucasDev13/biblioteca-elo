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

public interface MappingLayerObjects {

    UserDomain fromRequestToDomain(UserRequest request);
    User fromDomainToEntity(UserDomain domain);
    UserDomain fromEntityToDomain(User entiy);
    UserResponse fromDomainToResponse(UserDomain domain);

    BookDomain fromRequestToDomain(BookRequest request);
    Book fromDomainToEntity(BookDomain domain);
    BookDomain fromEntityToDomain(Book entiy);
    BookResponse fromDomainToResponse(BookDomain domain);

    LoanDomain fromEntityToDomain(Loan entiy);
    LoanResponse fromDomainToResponse(LoanDomain domain);

}
