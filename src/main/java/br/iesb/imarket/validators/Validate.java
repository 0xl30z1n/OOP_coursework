package br.iesb.imarket.validators;

import br.iesb.imarket.exception.ValidationException;

import java.util.regex.Pattern;

public abstract class Validate {
    protected static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public abstract void validate(String parameters) throws ValidationException;
}
