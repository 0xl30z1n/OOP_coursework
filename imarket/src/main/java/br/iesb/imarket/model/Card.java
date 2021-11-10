package br.iesb.imarket.model;

import br.iesb.imarket.enums.BannerType;
import br.iesb.imarket.enums.CardType;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Card {

//    private CardType type;
    private BannerType banner;
    private String cardNumber;
    private String name;
    private int cvv;
    private double limit;
    private String dateOfExpiry;

    public Card(BannerType banner, String cardNumber, int cvv, double limit, String name) {
        this.banner = banner;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.limit = limit;
        this.name = name;
    }

    public Card() {
    }

    public BannerType getBanner() {
        return banner;
    }

    public void setBanner(BannerType banner) {
        this.banner = banner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        YearMonth expiryDate = YearMonth.parse(dateOfExpiry, formatter);
        dateOfExpiry = String.valueOf(expiryDate);
        this.dateOfExpiry = dateOfExpiry;
    }
}