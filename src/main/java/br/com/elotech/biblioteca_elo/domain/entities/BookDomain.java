package br.com.elotech.biblioteca_elo.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class BookDomain {
    private UUID id;
    private String title;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private LocalDateTime registrationDate;
}
