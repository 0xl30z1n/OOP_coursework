package br.iesb.imarket.enums;

public enum CategoryType {
    ELETRONICS("Eletronics"),
    HYGIENE("Hygiene"),
    CLEANING("Cleaning"),
    GROCERS("Grocer's"),
    PERISHABLE("Perishable"),
    MEAT("Perishable/Meat"),
    HORTIFRUTI("Perishable/hortifruti");

    private final String description;

    CategoryType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
