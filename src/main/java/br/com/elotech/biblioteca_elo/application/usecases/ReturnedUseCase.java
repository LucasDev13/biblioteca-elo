package br.com.elotech.biblioteca_elo.application.usecases;

import br.com.elotech.biblioteca_elo.application.events.ReturnedBookEvent;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Returned;
import br.com.elotech.biblioteca_elo.infrastructure.persistence.repositories.returnedRepository.ReturnedRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public record ReturnedUseCase(
        ReturnedRepository repository
) {

    @EventListener
    public void createReturnedBook(ReturnedBookEvent event){
        Returned returned = Returned
                .builder()
                .user(event.getLoan().getUser())
                .book(event.getLoan().getBook())
                .loan(event.getLoan())
                .returnDate(LocalDate.now())
                .build();

        repository.save(returned);
    }
}
