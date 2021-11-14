package br.iesb.imarket.model;

import br.iesb.imarket.enums.BannerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    private Long id;
    private BannerType banner;
    private String cardNumber;
    private String name;
    private int cvv;
    private double limit;
    private String dateOfExpiry;

    public void setDateOfExpiry(String dateOfExpiry) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        YearMonth expiryDate = YearMonth.parse(dateOfExpiry, formatter);
        dateOfExpiry = String.valueOf(expiryDate);
        this.dateOfExpiry = dateOfExpiry;
    }
}