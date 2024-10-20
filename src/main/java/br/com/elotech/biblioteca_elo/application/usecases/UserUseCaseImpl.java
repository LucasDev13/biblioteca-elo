package br.com.elotech.biblioteca_elo.application.usecases;

import br.com.elotech.biblioteca_elo.application.exceptions.CreateUserException;
import br.com.elotech.biblioteca_elo.domain.entities.UserDomain;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces.UserUseCase;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers.MappingLayerObjects;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.User;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.userRepository.UserRepository;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.UserRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public record UserUseCaseImpl(
        MappingLayerObjects mapper,
        UserRepository repository
) implements UserUseCase {

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User userSaved;
        UserDomain domain = mapper.fromRequestToDomain(userRequest);
        //Preciso colocar a data de cadastro do usuário. Não pode ser maior que o dia atual.
        if(!Objects.isNull(domain)){
            userSaved = repository.save(mapper.fromDomainToEntity(domain));
        }
        throw new CreateUserException("Error create user.");
        //return mapper.fromDomainToResponse(mapper.fromEntityToDomain(userSaved));
    }

    @Override
    public UserResponse findById(String id) {
        User user = findUserById(UUID.fromString(id));
        return mapper.fromDomainToResponse(mapper.fromEntityToDomain(user));
    }

    @Override
    public List<UserResponse> listUsers() {
        List<User> allUsers = repository.findAll();
        return allUsers
                .stream()
                .map(user -> mapper.fromDomainToResponse(mapper.fromEntityToDomain(user)))
                .toList();
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
        User user = findUserById(UUID.fromString(id));
        if (!Objects.isNull(user)) {
            repository.delete(user);
        }
    }

    @Override
    public User findUserById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User id not found" + id));
    }
}
