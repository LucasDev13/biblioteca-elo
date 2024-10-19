package br.com.elotech.biblioteca_elo.application.validations;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@[\\w-\\.]+\\.[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email){
        if(Objects.isNull(email) || email.isEmpty()){
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
