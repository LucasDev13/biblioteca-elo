package br.com.elotech.biblioteca_elo.application.usecases;

import br.com.elotech.biblioteca_elo.application.events.ReturnedBookEvent;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces.LoanUseCase;
import br.com.elotech.biblioteca_elo.infrastructure.middleware.mappers.MappingLayerObjects;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Book;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Loan;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.User;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.enuns.StatusLoan;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.bookRepository.BookRepository;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.loanRepository.LoanRepository;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.userRepository.UserRepository;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.LoanResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public record LoanUseCaseImpl(
        LoanRepository loanRepository,
        UserRepository userRepository,
        BookRepository bookRepository,
        MappingLayerObjects mapper,
        ApplicationEventPublisher publisher
) implements LoanUseCase {

    @Override
    public LoanResponse createLoan(UUID userId, UUID bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        List<Loan> activeLoans = loanRepository.findByUserIdAndStatus(userId, StatusLoan.ACTIVE);
        if (!activeLoans.isEmpty()) {
            throw new IllegalStateException("Book is already loaned");
        }

        Loan newLoan = Loan.builder()
                .user(user)
                .book(book)
                .loanDate(LocalDate.now())
                .expectedReturnDateBook(getDaysLoan())
                .status(StatusLoan.ACTIVE)
                .build();

        Loan saved = loanRepository.save(newLoan);

        return mapper.fromDomainToResponse(mapper.fromEntityToDomain(saved));
    }

    @Override
    public Loan updateLoanStatus(UUID loanId, StatusLoan newStatus, LocalDate returnDate) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        // Atualiza o status e data de devolução (se o status for RETURNED)
        loan.setStatus(newStatus);
        if (newStatus == StatusLoan.RETURNED) {
            //Se for status RETURNED, precisa registrar na tabela tbl_returned a data de retorno e o status
            //Seria um bom momento para usar events
            //loan.setReturnDate(returnDate != null ? returnDate : LocalDate.now());
            publisher.publishEvent(new ReturnedBookEvent(this, loan));
        }

        return loanRepository.save(loan);
    }

    private LocalDate getDaysLoan() {
        return LocalDate.now().plusDays(5);
    }
}
