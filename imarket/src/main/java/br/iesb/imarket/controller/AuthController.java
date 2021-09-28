package br.iesb.imarket.controller;

import br.iesb.imarket.dto.UserDTO;
import br.iesb.imarket.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private AuthService service = new AuthService();


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO user){
        int result = service.signup_adm(user);

        switch (result){
            case 1:
                return ResponseEntity.badRequest().body("Nome do usuario invaalido!");
            case 2:
                return ResponseEntity.badRequest().body("E-mail do usuario inválido!");
            case 3:
                return ResponseEntity.badRequest().body("Senha do usuario inválida!");
            default:
                break;
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO user){
        String token = service.login_adm(user);

        if(token == null){
            ResponseEntity.notFound().build();
        }
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Autortization",token);

        return ResponseEntity.ok().headers(responseHeader).build();
    }
}
