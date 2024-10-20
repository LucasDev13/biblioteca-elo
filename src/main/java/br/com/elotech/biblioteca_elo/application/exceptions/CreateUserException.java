package br.com.elotech.biblioteca_elo.application.exceptions;

import java.io.Serial;

public class CreateUserException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CreateUserException(String msg) {
        super(msg);
    }
}
