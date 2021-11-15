package br.iesb.imarket.validators;

import br.iesb.imarket.exception.ValidationException;

public class EmailValidator extends Validate {

    @Override
    public void validate(String email) throws ValidationException{
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (!patternMatches(email, regexPattern)) {
            System.out.println("The Email address " + email + " is invalid");
            throw new ValidationException("Invalid email");
        }

    }
}
