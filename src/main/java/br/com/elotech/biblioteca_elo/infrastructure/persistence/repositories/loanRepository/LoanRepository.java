package br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.loanRepository;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Loan;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.enuns.StatusLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {

    List<Loan> findByUserIdAndStatus(UUID userId, StatusLoan statusLoan);
}
