package br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.enuns.StatusLoan;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_loans")
public class Loan {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "loan_date")
    private LocalDate loanDate;

    @Column(name = "expected_return_date_book")
    private LocalDate expectedReturnDateBook;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusLoan status;
}
