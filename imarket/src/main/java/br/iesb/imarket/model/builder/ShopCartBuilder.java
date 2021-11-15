package br.iesb.imarket.model.builder;

import br.iesb.imarket.model.ShopCart;
import br.iesb.imarket.model.ShopCartItem;

import java.util.Date;
import java.util.List;

public class ShopCartBuilder {
    private final ShopCart cart;

    private ShopCartBuilder(){
        this.cart = new ShopCart();
    }

    public static ShopCartBuilder builder(){
        return new ShopCartBuilder();
    }

    public ShopCartBuilder withId(Long id){
        cart.setId(id);
        return this;
    }

    public ShopCartBuilder withTotal(float total){
        cart.setTotal(total);
        return this;
    }

    public ShopCartBuilder withDate(Date data){
        cart.setLastModification(data);
        return this;
    }

    public ShopCartBuilder withItems(List<ShopCartItem> list){
        cart.setItems(list);
        return this;
    }

    public ShopCart build(){
        return this.cart;
    }
}
