package br.iesb.imarket;

import br.iesb.imarket.validators.EmailValidator;
import br.iesb.imarket.validators.Validate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImarketApplication.class, args);
    }
}
