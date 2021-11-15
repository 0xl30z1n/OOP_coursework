package br.iesb.imarket.dto.request;

import br.iesb.imarket.enums.BannerType;
import br.iesb.imarket.enums.CardType;
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
public class CardDTO {

    private CardType type;
    private BannerType banner;
    private String cardNumber;
    private String name;
    private String cvv;
    private double limit;
}