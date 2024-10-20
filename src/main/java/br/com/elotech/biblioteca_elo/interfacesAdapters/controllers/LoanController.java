package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers;

import br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces.LoanUseCase;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Loan;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.enuns.StatusLoan;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.LoanResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanUseCase loanUseCase;

    public LoanController(LoanUseCase loanUseCase) {
        this.loanUseCase = loanUseCase;
    }

    @PostMapping()
    public ResponseEntity<LoanResponse> createLoan(@RequestParam UUID userId, @RequestParam UUID bookId) {
        LoanResponse response = loanUseCase.createLoan(userId, bookId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{loanId}")
    public ResponseEntity<Loan> updateLoanStatus(
            @PathVariable UUID loanId,
            @RequestParam StatusLoan newStatus,
            @RequestParam(required = false) LocalDate returnDate) {

        Loan updatedLoan = loanUseCase.updateLoanStatus(loanId, newStatus, returnDate);
        return ResponseEntity.ok(updatedLoan);
    }
}
