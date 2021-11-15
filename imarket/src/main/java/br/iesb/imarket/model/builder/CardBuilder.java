package br.iesb.imarket.model.builder;

import br.iesb.imarket.enums.BannerType;
import br.iesb.imarket.enums.CardType;
import br.iesb.imarket.model.Card;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class CardBuilder {
    private final Card card;

    private CardBuilder(){
        this.card = new Card();
    }

    public static CardBuilder builder(){
        return new CardBuilder();
    }

    public CardBuilder idInCard(Long id){
        card.setId(id);
        return this;
    }

    public CardBuilder nameInCard(String name){
        card.setName(name);
        return this;
    }

    public CardBuilder withBanner(BannerType banner){
        card.setBanner(banner);
        return this;
    }

    public CardBuilder withType(CardType type){
        card.setType(type);
        return this;
    }

    public CardBuilder withCardNumber(String cardNumber){
        card.setCardNumber(cardNumber);
        return this;
    }

    public CardBuilder withCvv(String cvv){
        card.setCvv(cvv);
        return this;
    }

    public CardBuilder withLimit(double limit){
        card.setLimit(limit);
        return this;
    }

    public Card build(){
        return this.card;
    }
}
