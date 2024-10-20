package br.com.elotech.biblioteca_elo.domain.entities;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.User;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.enuns.StatusLoan;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LoanDomain {

    private User user;
    private Book book;
    private LocalDate loanDate;
    private LocalDate expectedReturnDateBook;
    private StatusLoan status;

}
