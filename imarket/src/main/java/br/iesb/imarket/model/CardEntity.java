package br.iesb.imarket.model;

import br.iesb.imarket.enums.BannerType;
import br.iesb.imarket.enums.CardType;

public class CardEntity {

    private CardType type;
    private BannerType banner;
    private String numberCard;
    private int cvv;
    private double limit;

    public CardEntity(CardType type, BannerType banner, String numberCard, int cvv,double limit) {
        this.type = type;
        this.banner = banner;
        this.numberCard = numberCard;
        this.cvv = cvv;
        this.limit = limit;
    }

    public CardEntity() {
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public BannerType getBanner() {
        return banner;
    }

    public void setBanner(BannerType banner) {
        this.banner = banner;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
