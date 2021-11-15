package br.iesb.imarket.dto.request.builder;

import br.iesb.imarket.dto.request.CardDTO;
import br.iesb.imarket.enums.BannerType;
import br.iesb.imarket.enums.CardType;

public class CardDTOBuilder {
    private final CardDTO card;

    private CardDTOBuilder(){
        this.card = new CardDTO();
    }

    public static CardDTOBuilder builder(){
        return new CardDTOBuilder();
    }

    public CardDTOBuilder nameInCard(String name){
        card.setName(name);
        return this;
    }

    public CardDTOBuilder withBanner(BannerType banner){
        card.setBanner(banner);
        return this;
    }

    public CardDTOBuilder withType(CardType type){
        card.setType(type);
        return this;
    }

    public CardDTOBuilder withCardNumber(String cardNumber){
        card.setCardNumber(cardNumber);
        return this;
    }

    public CardDTOBuilder withLimit(double limit){
        card.setLimit(limit);
        return this;
    }

    public CardDTOBuilder withCvv(String cvv){
        card.setCvv(cvv);
        return this;
    }

    public CardDTO build(){
        return this.card;
    }
}
