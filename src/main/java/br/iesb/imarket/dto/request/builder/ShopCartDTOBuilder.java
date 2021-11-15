package br.iesb.imarket.dto.request.builder;

import br.iesb.imarket.dto.request.ShopCartDTO;
import br.iesb.imarket.dto.request.ShopCartItemDTO;


import java.util.Date;
import java.util.List;

public class ShopCartDTOBuilder {
    private final ShopCartDTO cart;

    private ShopCartDTOBuilder(){
        this.cart = new ShopCartDTO();
    }

    public static ShopCartDTOBuilder builder(){
        return new ShopCartDTOBuilder();
    }

    public ShopCartDTOBuilder withId(Long id){
        cart.setId(id);
        return this;
    }

    public ShopCartDTOBuilder withTotal(float total){
        cart.setTotal(total);
        return this;
    }

    public ShopCartDTOBuilder withDate(Date data){
        cart.setLastModification(data);
        return this;
    }

    public ShopCartDTOBuilder withItems(List<ShopCartItemDTO> list){
        cart.setItems(list);
        return this;
    }

    public ShopCartDTO build(){
        return this.cart;
    }
}
