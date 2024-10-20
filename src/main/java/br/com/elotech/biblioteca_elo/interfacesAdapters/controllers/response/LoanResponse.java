package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.User;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.enuns.StatusLoan;

import java.time.LocalDate;

public record LoanResponse(
        User user,
        Book book,
        LocalDate loanDate,
        LocalDate expectedReturnDateBook,
        StatusLoan status
) {
}
