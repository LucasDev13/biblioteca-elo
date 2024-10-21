package br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_books")
public class Book {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(unique = true)
    private String isbn;

    @Column(nullable = false)
    private String category;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "registration_date", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime registrationDate;

    @Column(name = "create_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @PastOrPresent
    private Instant createAt;

    @Column(name = "update_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @PastOrPresent
    private Instant updateAt;

    @PrePersist
    public void prePersist(){
        createAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        updateAt = Instant.now();
    }
}

