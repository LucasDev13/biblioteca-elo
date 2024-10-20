package br.com.elotech.biblioteca_elo.interfacesAdapters.controllerAdvice;

import br.com.elotech.biblioteca_elo.application.validations.FieldErrorDetails;

import java.time.Instant;
import java.util.List;

public record ValidationErrorResponse(
        Instant timestamp,
        Integer status,
        String error,
        String path,
        List<FieldErrorDetails> fieldErrors
) {
}
