package br.iesb.imarket.model.payment;

import br.iesb.imarket.model.Card;

public class CreditCardStrategy implements PaymentStrategy{
    private final Card card;

    public CreditCardStrategy(Card card){
        this.card = card;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + "paid with credit card in name: " + card.getName());
    }
}
