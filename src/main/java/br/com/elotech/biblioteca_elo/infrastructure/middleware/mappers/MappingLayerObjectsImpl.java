package br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers;

import br.com.elotech.biblioteca_elo.domain.entities.UserDomain;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.User;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request.UserRequest;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MappingLayerObjectsImpl implements MappingLayerObjects {
    @Override
    public UserDomain fromRequestToDomain(UserRequest request) {
        if(!Objects.isNull(request)) {
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
        return User.builder()
                .name(domain.getName())
                .email(domain.getEmail())
                .phoneNumber(domain.getPhoneNumber())
                .build();
    }

    @Override
    public UserDomain fromEntityToDomain(User entiy) {
        return UserDomain.builder()
                .name(entiy.getName())
                .email(entiy.getEmail())
                .phoneNumber(entiy.getPhoneNumber())
                .build();
    }

    @Override
    public UserResponse fromDomainToResponse(UserDomain domain) {
        return new UserResponse(
                domain.getName(),
                domain.getEmail(),
                domain.getPhoneNumber()
        );
    }
}
