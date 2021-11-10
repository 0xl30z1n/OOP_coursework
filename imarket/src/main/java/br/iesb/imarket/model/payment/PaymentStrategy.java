package br.iesb.imarket.model.payment;

public interface PaymentStrategy {
    public void pay(double amount);
}
