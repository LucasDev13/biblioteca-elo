package br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.userRepository;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
