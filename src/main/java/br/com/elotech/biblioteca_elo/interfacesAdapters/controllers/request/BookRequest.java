package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Objects;

public record BookRequest(
        @NotEmpty(message = "Não pode estar vazio ou null!")
        String title,

        @NotEmpty(message = "Não pode estar vazio ou null!")
        String author,

        @NotEmpty(message = "Não pode estar vazio ou null!")
        String isbn,

        @NotEmpty(message = "Não pode estar vazio ou null!")
        Category category,

        @PastOrPresent(message = "A data precisa estar no passado ou no presente")
        LocalDate publicationDate) {

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                BookRequest that = (BookRequest) o;
                return Objects.equals(isbn, that.isbn) && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(category, that.category) && Objects.equals(publicationDate, that.publicationDate);
        }

        @Override
        public int hashCode() {
                return Objects.hash(title, author, isbn, category, publicationDate);
        }
}
