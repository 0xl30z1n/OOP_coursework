package br.iesb.imarket.enums;

public enum CardType {
    DEBIT("Debit"),
    CREDIT("Credit"),
    PREPAID("Pre-paid");

    private final String description;


    CardType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}