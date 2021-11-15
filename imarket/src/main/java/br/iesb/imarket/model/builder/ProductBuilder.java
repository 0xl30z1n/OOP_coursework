package br.iesb.imarket.model.builder;

import br.iesb.imarket.enums.CategoryType;
import br.iesb.imarket.model.Product;

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
    }

    public ProductBuilder brandInProduct(String brand){
        product.setBrand(brand);
        return this;
    }

    public ProductBuilder withPrice(float price){
        product.setPrice(price);
        return this;
    }

    public ProductBuilder descriptionInProduct(String description){
        product.setDescription(description);
        return this;
    }

    public ProductBuilder withPromotion(boolean promotion){
        product.setPromotion(promotion);
        return this;
    }

    public ProductBuilder withPercent(float percent){
        product.setPercent(percent);
        return this;
    }

    public ProductBuilder withCategory(CategoryType category){
        product.setCategory(category);
        return this;
    }

    public ProductBuilder withQuantity(int quantity){
        product.setQuantity(quantity);
        return this;
    }

    public Product build(){
        return this.product;
    }

}
