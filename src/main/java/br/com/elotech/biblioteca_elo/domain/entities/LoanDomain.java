package br.com.elotech.biblioteca_elo.domain.entities;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.enuns.StatusLoan;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class LoanDomain {

    private UUID userId;
    private UUID bookId;
    private LocalDate loanDate;
    private LocalDate expectedReturnDateBook;
    private StatusLoan status;

}
