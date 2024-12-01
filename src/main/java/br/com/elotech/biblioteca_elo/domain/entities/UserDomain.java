package br.com.elotech.biblioteca_elo.domain.entities;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Person;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDomain {

    private Person person;
}
