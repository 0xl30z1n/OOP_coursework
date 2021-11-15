package br.iesb.imarket.controller;

import br.iesb.imarket.dto.request.CardDTO;
import br.iesb.imarket.dto.response.MessageResponseDTO;
import br.iesb.imarket.dto.response.MessageSingletron;
import br.iesb.imarket.exception.CardNotFoundException;
import br.iesb.imarket.exception.UserNotFoundException;
import br.iesb.imarket.exception.UserUnauthorizedException;
import br.iesb.imarket.service.AuthService;
import br.iesb.imarket.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardController {
    @Autowired
    private CardService serviceCard;

    @Autowired
    private AuthService serviceAuth;

    private MessageSingletron msg = MessageSingletron.getMessageSingletron();

    @GetMapping("/card/{id}")
    public CardDTO getCardUser(@PathVariable Long id) throws CardNotFoundException, UserUnauthorizedException, UserNotFoundException {
        verfiyUser(id);
        return serviceCard.fetchUserCard(id);
    }

    @PostMapping("/card/save/{id}")
    public ResponseEntity<MessageResponseDTO> saveCardUser(@PathVariable Long id, @RequestBody CardDTO cardDto) throws UserUnauthorizedException, UserNotFoundException{
        verfiyUser(id);
        serviceCard.saveUserCard(cardDto,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg.withMessage("User card created").build());
    }

    @DeleteMapping("/card/delete/{id}")
    public ResponseEntity deleteCardUser(@PathVariable Long id) throws CardNotFoundException, UserUnauthorizedException, UserNotFoundException {
        verfiyUser(id);
        serviceCard.deleteUserCard(id);
        return ResponseEntity.noContent().build();
    }

    private void verfiyUser(Long idUser) throws UserUnauthorizedException, UserNotFoundException {
        if(serviceAuth.getLogById(idUser).isAcess()){
            throw new UserUnauthorizedException("Adm unauthorized Whit ID "+ idUser);
        }
    }
}
