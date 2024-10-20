package br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.returnedRepository;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Returned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReturnedRepository extends JpaRepository<Returned, UUID> {
}
