package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response;

import br.com.elotech.biblioteca_elo.domain.entities.Item;

import java.util.List;

public record BookResponseClient(
        List<Item> items
) {
}
