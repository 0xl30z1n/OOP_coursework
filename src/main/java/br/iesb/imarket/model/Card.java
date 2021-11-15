package br.iesb.imarket.model;

import br.iesb.imarket.enums.BannerType;
import br.iesb.imarket.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card")
public class Card {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "banner", nullable = false)
    private BannerType banner;

    @Column(name = "type", nullable = false)
    private CardType type;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @Column(name = "limite", nullable = false)
    private double limit;

}