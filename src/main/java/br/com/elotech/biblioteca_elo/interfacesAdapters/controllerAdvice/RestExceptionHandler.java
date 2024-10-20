package br.com.elotech.biblioteca_elo.interfacesAdapters.controllerAdvice;

import br.com.elotech.biblioteca_elo.application.exceptions.CreateUserException;
import br.com.elotech.biblioteca_elo.application.validations.FieldErrorDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler{

    @ExceptionHandler(CreateUserException.class)
    public ResponseEntity<RestErrorMessage> createUserError(CreateUserException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RestErrorMessage errorMessage = new RestErrorMessage(
                Instant.now(),
                status.value(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> fieldNotValidError(MethodArgumentNotValidException ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<FieldErrorDetails> fieldErrors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.add(new FieldErrorDetails(fieldName, errorMessage));
        });

        ValidationErrorResponse errorResponse = new ValidationErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                request.getRequestURI(),
                fieldErrors
        );
        return ResponseEntity.status(status).body(errorResponse);
    }
}
