package br.iesb.imarket.model.builder;

import br.iesb.imarket.model.ShopCartItem;

public class ShopCartItemBuilder {
    private final ShopCartItem shopcartitem;


    private ShopCartItemBuilder(){
        this.shopcartitem = new ShopCartItem();
    }

    public static ShopCartItemBuilder builder(){
        return new ShopCartItemBuilder();
    }

    public ShopCartItemBuilder withId(int id){
        shopcartitem.setId(id);
        return this;
    }

    public ShopCartItemBuilder withQuantity(int quantity){
        shopcartitem.setQuantity(quantity);
        return this;
    }

    public ShopCartItemBuilder withPrice(float price){
        shopcartitem.setPrice(price);
        return this;
    }
    public ShopCartItem build(){
        return this.shopcartitem;
    }

}