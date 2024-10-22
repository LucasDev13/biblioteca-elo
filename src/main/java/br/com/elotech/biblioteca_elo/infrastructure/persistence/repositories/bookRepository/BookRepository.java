package br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.bookRepository;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("SELECT b FROM Book b WHERE b.category.id IN :categories AND b.id NOT IN (SELECT l.book.id FROM Loan l WHERE l.user.id = :userId)")
    List<Book> findBooksByCategoryAndNotBorrowedByUser(@Param("categories") List<UUID> categories, UUID userId);
}
