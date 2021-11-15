package br.iesb.imarket.dto.request.builder;

import br.iesb.imarket.dto.request.ShopCartItemDTO;

public class ShopCartItemDTOBuilder {
    private final ShopCartItemDTO itemDto;

    private ShopCartItemDTOBuilder(){
        this.itemDto = new ShopCartItemDTO();
    }

    public static ShopCartItemDTOBuilder builder(){
        return new ShopCartItemDTOBuilder();
    }

    public ShopCartItemDTOBuilder withId(int id){
        itemDto.setId(id);
        return this;
    }

    public ShopCartItemDTOBuilder withQuantity(int quantity){
        itemDto.setQuantity(quantity);
        return this;
    }

    public ShopCartItemDTOBuilder withPrice(float price){
        itemDto.setPrice(price);
        return this;
    }

    public ShopCartItemDTO build(){
        return this.itemDto;
    }
}
