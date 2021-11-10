package br.iesb.imarket.model.payment;

public class PixStrategy implements PaymentStrategy{
    private final String consumerName;
    private final String keyCompany;
    private final String transactionId;

    public PixStrategy(String name, String pixKey, String transactionId){
        this.consumerName = name;
        this.keyCompany = pixKey;
        this.transactionId = transactionId;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " paid using Pix in name: " + consumerName);
    }
}
