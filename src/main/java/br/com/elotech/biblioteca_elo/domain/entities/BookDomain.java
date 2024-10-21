package br.com.elotech.biblioteca_elo.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class BookDomain {
    private String title;
    private String author;
    private String isbn;
    private String category;
    private LocalDate publicationDate;
    private LocalDateTime registrationDate;
}
