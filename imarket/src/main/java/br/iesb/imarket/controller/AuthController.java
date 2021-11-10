package br.iesb.imarket.controller;

import br.iesb.imarket.dto.request.UserDTO;
import br.iesb.imarket.dto.response.MessageResponseDTO;
import br.iesb.imarket.service.AuthService;
import br.iesb.imarket.validators.EmailValidator;
import br.iesb.imarket.validators.Validate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private AuthService service = new AuthService();
    private MessageResponseDTO message = new MessageResponseDTO();

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDTO> signup(@RequestBody UserDTO user){
        Validate emailValidator = new EmailValidator();


        int result = service.signup(user);

        switch (result){
            case 1:
                message.setMessage("Nome do usuario inválido!");
                return ResponseEntity.badRequest().body(message);
            case 2:
                message.setMessage("E-mail do usuario inválido!");
                return ResponseEntity.badRequest().body(message);
            case 3:
                message.setMessage("Senha do usuario inválida!");
                return ResponseEntity.badRequest().body(message);
            default:
                break;
        }
        message.setMessage("User created");
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO user){
        String token = service.login(user);

        if(token == null){
            return ResponseEntity.notFound().build();
        }
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Autortization",token);

        return ResponseEntity.ok().headers(responseHeader).build();
    }
}