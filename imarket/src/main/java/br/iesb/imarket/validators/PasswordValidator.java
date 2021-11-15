package br.iesb.imarket.validators;

import br.iesb.imarket.exception.ValidationException;

public class PasswordValidator extends Validate{
    @Override
    public void validate(String password) throws ValidationException {
        String regexPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

        if (!patternMatches(password, regexPattern)) {
            throw new ValidationException("Invalid password");
        }
    }
}
