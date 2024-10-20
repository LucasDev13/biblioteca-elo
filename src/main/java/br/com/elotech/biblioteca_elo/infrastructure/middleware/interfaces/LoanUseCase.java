package br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Loan;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.enuns.StatusLoan;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.LoanResponse;

import java.time.LocalDate;
import java.util.UUID;

public interface LoanUseCase {
    LoanResponse createLoan(UUID userId, UUID bookId);
    Loan updateLoanStatus(UUID loanId, StatusLoan newStatus, LocalDate returnDate);
}
