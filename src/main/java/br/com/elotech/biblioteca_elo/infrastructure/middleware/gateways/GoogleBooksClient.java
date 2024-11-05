package br.com.elotech.biblioteca_elo.infrastructure.middleware.gateways;

import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "googleBooks", url = "https://www.googleapis.com/books/v1")
public interface GoogleBooksClient {

    @GetMapping("/volumes")
    BookResponseClient searchBooks(@RequestParam("q") String query);
}
