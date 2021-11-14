package br.iesb.imarket.dto.request;

import br.iesb.imarket.enums.BannerType;
import br.iesb.imarket.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private Long id;
    private CardType type;
    private BannerType banner;
    private String numberCard;
    private int cvv;
    private long limit;
}