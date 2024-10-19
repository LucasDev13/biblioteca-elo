package br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces;

import br.com.elotech.biblioteca_elo.domain.entities.UserDomain;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.User;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.UserRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.UserResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserUseCase {

    UserResponse createUser(UserRequest userRequest);
    UserResponse findUserById(String id);
    User findUserById(UUID id);
    List<UserResponse> listUsers();
    UserResponse updateUser(String id, UserRequest userRequest);
    void deleteUser(String id);
}
