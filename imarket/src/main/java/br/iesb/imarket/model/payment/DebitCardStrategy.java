package br.iesb.imarket.model.payment;

import br.iesb.imarket.model.Card;

public class DebitCardStrategy implements PaymentStrategy{
    private final Card card;

    public DebitCardStrategy(Card card){
        this.card = card;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " paid with debit card in name: " + card.getName());
    }
}
