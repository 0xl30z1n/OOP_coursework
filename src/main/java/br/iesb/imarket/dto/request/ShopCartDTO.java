package br.iesb.imarket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShopCartDTO {
    private Long id;
    private List<ShopCartItemDTO> items = new ArrayList<>();
    private float total;
    private Date lastModification;
}