package br.iesb.imarket.validators;

public class EmailValidator extends Validate {

    @Override
    protected void validate(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (!patternMatches(email, regexPattern)) {
            System.out.println("The Email address " + email + " is invalid");
            throw new IllegalArgumentException("Invalid email");
        }

    }
}
