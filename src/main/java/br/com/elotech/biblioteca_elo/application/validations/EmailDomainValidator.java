package br.com.elotech.biblioteca_elo.application.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.regex.Pattern;

public class EmailDomainValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){
        if(Objects.isNull(email) || email.isEmpty() || !Pattern.matches(EMAIL_REGEX, email)){
            return false;
        }
        String domain = email.substring(email.lastIndexOf("@") + 1);
        String topLevelDomain = domain.substring(domain.lastIndexOf(".") + 1);

        return topLevelDomain.length() >= 3;
    }
}
