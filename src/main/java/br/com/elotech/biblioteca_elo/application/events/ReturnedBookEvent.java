package br.com.elotech.biblioteca_elo.application.events;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Loan;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ReturnedBookEvent extends ApplicationEvent {

    private final Loan loan;

    public ReturnedBookEvent(Object source, Loan loan) {
        super(source);
        this.loan = loan;
    }
}
