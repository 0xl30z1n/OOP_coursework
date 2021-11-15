package br.iesb.imarket.controller;

import br.iesb.imarket.dto.request.ShopCartDTO;
import br.iesb.imarket.dto.response.MessageResponseDTO;
import br.iesb.imarket.dto.response.MessageSingletron;
import br.iesb.imarket.exception.UserNotFoundException;
import br.iesb.imarket.exception.UserUnauthorizedException;
import br.iesb.imarket.service.AuthService;
import br.iesb.imarket.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopCartController {
    @Autowired
    private ShopCartService service;

    @Autowired
    private AuthService serviceAuth;

    private MessageSingletron msg = MessageSingletron.getMessageSingletron();

    @GetMapping("/cart/{id}")
    public ShopCartDTO getCart(@PathVariable Long id) throws UserUnauthorizedException, UserNotFoundException{
        verfiyUser(id);
        return service.getCart(id);
    }

    @PostMapping("/cart/save/{id}")
    public ResponseEntity<MessageResponseDTO> saveCart(@PathVariable Long id,@RequestBody ShopCartDTO cart) throws UserUnauthorizedException, UserNotFoundException{
        verfiyUser(id);
        service.saveCart(id,cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg.withMessage("Cart created").build());
    }
    @DeleteMapping("/cart/delete/{id}")
    public ResponseEntity deleteCart(@PathVariable Long id) throws UserUnauthorizedException, UserNotFoundException{
        verfiyUser(id);
        service.deleteCart(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private void verfiyUser(Long idUser) throws UserUnauthorizedException, UserNotFoundException {
        if(serviceAuth.getLogById(idUser).isAcess()){
            throw new UserUnauthorizedException("Adm unauthorized Whit ID "+ idUser);
        }
    }
}
