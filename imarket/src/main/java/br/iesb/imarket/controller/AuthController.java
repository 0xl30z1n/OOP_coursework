package br.iesb.imarket.controller;

import br.iesb.imarket.dto.request.UserDTO;
import br.iesb.imarket.dto.request.UserLoginDTO;
import br.iesb.imarket.dto.response.MessageResponseDTO;
import br.iesb.imarket.dto.response.MessageSingletron;
import br.iesb.imarket.exception.UserBadRequestException;
import br.iesb.imarket.exception.UserNotFoundException;
import br.iesb.imarket.service.AuthService;
import br.iesb.imarket.validators.EmailValidator;
import br.iesb.imarket.validators.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private AuthService service;
    private MessageSingletron msg = MessageSingletron.getMessageSingletron();

    @GetMapping("/users")
    public List<UserDTO> getUsers() throws UserNotFoundException {
        return service.getUsers();
    }

    @GetMapping("/users/login")
    public List<UserLoginDTO> getUsersLog() throws UserNotFoundException{
        return service.getUsersLog();
    }

    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable Long id) throws UserNotFoundException{
        return service.getUserById(id);
    }

    @GetMapping("/user/login/{id}")
    public UserLoginDTO getUserLoginById(@PathVariable Long id) throws UserNotFoundException{
        return service.getLogById(id);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDTO> signup(@RequestBody UserDTO user) throws UserBadRequestException{
        Validate emailValidator = new EmailValidator();

        int result = service.signup(user);

        switch (result){
            case 1:
                return ResponseEntity.badRequest().body(msg.withMessage("Nome do usuario inválido!").build());
            case 2:
                return ResponseEntity.badRequest().body(msg.withMessage("E-mail do usuario inválido!").build());
            case 3:
                return ResponseEntity.badRequest().body(msg.withMessage("Senha do usuario inválida!").build());
            default:
                break;
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(msg.withMessage("User created").build());
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