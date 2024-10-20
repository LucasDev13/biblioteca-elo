package br.com.elotech.biblioteca_elo.interfacesAdapters.controllerAdvice;

import java.time.Instant;

public record RestErrorMessage(
        Instant timestamp,
        Integer status,
        String message,
        String path
) {
}
