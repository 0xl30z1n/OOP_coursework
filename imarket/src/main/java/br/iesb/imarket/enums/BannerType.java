package br.iesb.imarket.enums;

public enum BannerType {
    VISA("Visa"),
    ELO("Elo"),
    MASTERCARD("Master Card"),
    AMERICAN_EXPRESS("American Express"),
    DISCOVER_NETWORK("Discover Network"),
    GOOD_CARD("Good Card"),
    SODEXO("Sodexo");

    private final String description;

    BannerType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
