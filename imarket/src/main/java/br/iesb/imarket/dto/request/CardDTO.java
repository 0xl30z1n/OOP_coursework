package br.iesb.imarket.dto.request;

import br.iesb.imarket.enums.BannerType;
import br.iesb.imarket.enums.CardType;

public class CardDTO {
    private CardType type;
    private BannerType banner;
    private String numberCard;
    private int cvv;
    private long limit;

    public CardDTO(CardType type, BannerType banner, String numberCard, int cvv, long limit) {
        this.type = type;
        this.banner = banner;
        this.numberCard = numberCard;
        this.cvv = cvv;
        this.limit = limit;
    }

    public CardDTO(){
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

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }
}