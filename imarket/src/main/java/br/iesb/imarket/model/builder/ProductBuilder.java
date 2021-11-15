package br.iesb.imarket.model.builder;

public class ProductBuilder {
    private final Product product;

    private ProductBuilder(){
        this.product = new Product();
    }

    public static ProductBuilder builder(){
        return new ProductBuilder();
    }

    public ProductBuilder nameInProduct(String name){
        product.setName(name);
        return this;
    }SS

    public ProductBuilder brandInProduct(String brand){
        product.setbrand(brand);
        return this;
    }

    public ProductBuilder withPrice(float price){
        product.setprice(price);
        return this;
    }

    public ProductBuilder descriptionInProduct(String description){
        product.setdescription(description);
        return this;
    }

    public ProductBuilder withPromotion(boolean promotion){
        product.setpromotion(price);
        return this;
    }

    public ProductBuilder withPercent(float percent){
        product.setpercent(percent);
        return this;
    }

    public ProductBuilder withCategory(CategoryType category){
        product.setCategory(category);
        return this;
    }

    public ProductBuilder withQuantity(int quantity){
        product.setquantity(quantity);
        return this;
    }

    public Product build(){
        return this.product;
    }

}
