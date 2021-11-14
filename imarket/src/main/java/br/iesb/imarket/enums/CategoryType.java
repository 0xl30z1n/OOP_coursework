package br.iesb.imarket.enums;

public enum CategoryType {
    ELETRONICS("Eletronics");

    private final String description;

    CategoryType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
