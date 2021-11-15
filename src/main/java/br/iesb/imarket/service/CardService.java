package br.iesb.imarket.service;

import br.iesb.imarket.dto.request.CardDTO;
import br.iesb.imarket.dto.request.builder.CardDTOBuilder;
import br.iesb.imarket.enums.BannerType;
import br.iesb.imarket.enums.CardType;
import br.iesb.imarket.exception.CardBadRequestException;
import br.iesb.imarket.exception.CardNotFoundException;
import br.iesb.imarket.model.Card;
import br.iesb.imarket.model.builder.CardBuilder;
import br.iesb.imarket.repository.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private CardRepo repository;

    public CardDTO fetchUserCard(Long id) throws CardNotFoundException{
        return verifyIfCardExists(id);
    }

    public void saveUserCard(CardDTO card, Long id){
        Card entity = dtoForEntity(card,id);
        repository.save(entity);
    }

    public void deleteUserCard(Long id) throws CardNotFoundException{
        verifyIfCardExists(id);
        repository.deleteById(id);
    }

    private CardDTO verifyIfCardExists(Long id) throws CardNotFoundException {
        Card card = repository.findById(id).orElseThrow(()->new CardNotFoundException("User card not found"));
        return entityForDTO(card);
    }

    private CardDTO entityForDTO(Card card){
        CardDTOBuilder builder = CardDTOBuilder.builder();
        CardDTO dto = builder
                .nameInCard(card.getName())
                .withBanner(card.getBanner())
                .withType(card.getType())
                .withCardNumber(card.getCardNumber())
                .withLimit(card.getLimit())
                .withCvv(card.getCvv())
                .build();
        return dto;
    }

    private Card dtoForEntity(CardDTO card,Long id){
        CardBuilder builder = CardBuilder.builder();
        Card entity = builder
                .idInCard(id)
                .nameInCard(card.getName())
                .withBanner(card.getBanner())
                .withType(card.getType())
                .withCardNumber(card.getCardNumber())
                .withLimit(card.getLimit())
                .withCvv(card.getCvv())
                .build();
        return entity;
    }

    private void rulesCard(CardDTO card) throws CardBadRequestException {
        if(card.getName().trim().equals("") || card.getName().trim().split(" ").length < 2 || verifyFirstCaracName(card.getName().trim().split(" ")) || verifyNumber(card.getName().trim()) || verifySpecial(card.getName().trim())){
            throw new CardBadRequestException("Card name invalid");
        }
        if(card.getCvv().length() < 3){
            throw new CardBadRequestException("Card cvv invalid");
        }
        if(card.getCardNumber().length() < 13){
            throw new CardBadRequestException("Card number invalid");
        }
        if(card.getLimit() < 0){
            throw new CardBadRequestException("Card limit invalid");
        }

        verifyType(card.getType());
        verifyBanner(card.getBanner());
    }

    private void verifyType(CardType type) throws CardBadRequestException{
        CardType[] values = CardType.values();

        for(CardType t: values){
            if(t.name().equals(type.name())){
                return;
            }
        }

        throw new CardBadRequestException("Card type invalid");
    }

    private void verifyBanner(BannerType type) throws CardBadRequestException{
        BannerType[] values = BannerType.values();

        for(BannerType t: values){
            if(t.name().equals(type.name())){
                return;
            }
        }

        throw new CardBadRequestException("Card banner invalid");
    }


    private boolean verifySpecial(String str){
        if(str.contains(".") || str.contains("_") || str.contains("$") || str.contains("@") || str.contains("#") || str.contains("%") || str.contains("*") || str.contains("&")){
            return true;
        }
        return false;
    }
    private boolean verifyNumber(String str){
        if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
            return true;
        }
        return false;
    }
    private boolean verifyFirstCaracName(String[] array){
        for(String aux: array){
            if(!(aux.substring(0,1).equals(aux.substring(0,1).toUpperCase())) || firstVerifyNumber(aux) || firstVerifySpecial(aux)){
                return true;
            }
        }
        return false;
    }
    private boolean firstVerifyNumber(String str){
        if(str.substring(0,1).equals("0") || str.substring(0,1).equals("1") || str.substring(0,1).equals("2") || str.substring(0,1).equals("3") || str.substring(0,1).equals("4") || str.substring(0,1).equals("5") || str.substring(0,1).equals("6") || str.substring(0,1).equals("7") || str.substring(0,1).equals("8") || str.substring(0,1).equals("9")){
            return true;
        }
        return false;
    }
    private boolean firstVerifySpecial(String str){
        if(str.substring(0,1).equals("@") || str.substring(0,1).equals("#") || str.substring(0,1).equals("$") || str.substring(0,1).equals("%") || str.substring(0,1).equals("&") || str.substring(0,1).equals("*") || str.substring(0,1).equals("_")){
            return true;
        }
        return false;
    }
}
