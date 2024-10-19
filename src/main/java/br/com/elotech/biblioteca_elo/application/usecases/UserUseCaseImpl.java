package br.com.elotech.biblioteca_elo.application.usecases;

import br.com.elotech.biblioteca_elo.domain.entities.UserDomain;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces.UserUseCase;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers.MappingLayerObjects;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.User;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.userRepository.UserRepository;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.UserRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserUseCaseImpl implements UserUseCase {

    private final MappingLayerObjects mapper;
    private final UserRepository repository;

    public UserUseCaseImpl(MappingLayerObjects mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        var domain = mapper.fromRequestToDomain(userRequest);
        User userSaved = repository.save(mapper.fromDomainToEntity(domain));
        return mapper.fromDomainToResponse(mapper.fromEntityToDomain(userSaved));
    }

    @Override
    public UserResponse findUserById(String id) {
        return null;
    }

    @Override
    public List<UserResponse> listUsers() {
        return List.of();
    }

    @Override
    public UserResponse updateUser(String id, UserRequest userRequest) {
        User user = findUserById(UUID.fromString(id));
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPhoneNumber(user.getPhoneNumber());

        repository.save(user);
        return mapper.fromDomainToResponse(mapper.fromEntityToDomain(user));
    }

    @Override
    public void deleteUser(String id) {
        //necessirta de implementação
    }

    @Override
    public User findUserById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User id not found" + id));
    }
}
