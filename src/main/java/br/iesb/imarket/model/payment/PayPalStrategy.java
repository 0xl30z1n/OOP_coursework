package br.iesb.imarket.model.payment;

public class PayPalStrategy implements PaymentStrategy{
    private final String name;
    private final String email;
    private final String password;

    public PayPalStrategy(String name, String email, String passwd){
        this.name = name;
        this.email = email;
        this.password = passwd;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " paid using Paypal in name: " + name);
    }
}
