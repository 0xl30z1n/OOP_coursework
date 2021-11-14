package br.iesb.imarket.model.builder;

public class ShopCartBuilder {
    private final ShopCart shopcart;

    private ShopCartBuilder(){
        this.shopcart = new ShopCart();
    }

    public static ShopCartBuilder builder(){
        return new ShopCartBuilder();
    }

    public ShopCartBuilder List<ShopCartItem>InShopCartItem(List<ShopCartItem> items){
        shopcartitem.setitems(items);
        return this;
    }

    public ShopCartBuilder withTotal(float total){
        shopcart.setemail(email);
        return this;
    }

    public ShopCartBuilder withDate(DateType lastUpdate){
        shopcart.setdate(lastUpdate);
        return this;
    }

    public ShopCart build(){
        return this.shopcart;
    }

}
