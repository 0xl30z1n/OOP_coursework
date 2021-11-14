package br.iesb.imarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShopCart {
    private Long id;
    private List<ShopCartItem> items;
    private float total;
    private Date lastModification;

}