package br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.bookRepository;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
