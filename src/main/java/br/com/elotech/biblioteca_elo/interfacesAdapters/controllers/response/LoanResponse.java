package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.enuns.StatusLoan;

import java.time.LocalDate;
import java.util.UUID;

public record LoanResponse(
        UUID userId,
        UUID bookId,
        LocalDate loanDate,
        LocalDate expectedReturnDateBook,
        StatusLoan status
) {
}
