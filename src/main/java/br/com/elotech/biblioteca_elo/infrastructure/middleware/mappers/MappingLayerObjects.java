package br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers;

import br.com.elotech.biblioteca_elo.domain.entities.UserDomain;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.User;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.UserRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.UserResponse;

public interface MappingLayerObjects {

    UserDomain fromRequestToDomain(UserRequest request);
    User fromDomainToEntity(UserDomain domain);
    UserDomain fromEntityToDomain(User entiy);
    UserResponse fromDomainToResponse(UserDomain domain);
}
