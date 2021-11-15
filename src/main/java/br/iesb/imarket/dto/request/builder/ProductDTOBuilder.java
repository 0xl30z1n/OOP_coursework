package br.iesb.imarket.dto.request.builder;


import br.iesb.imarket.dto.request.ProductDTO;

public class ProductDTOBuilder {
    private final ProductDTO product;

    private ProductDTOBuilder(){
        this.product = new ProductDTO();
    }

    public static ProductDTOBuilder builder(){
        return new ProductDTOBuilder();
    }

    public ProductDTOBuilder withName(String name){
        product.setName(name);
        return this;
    }

    public ProductDTOBuilder withBrand(String brand){
        product.setBrand(brand);
        return this;
    }

    public ProductDTOBuilder withPrice(float price){
        product.setPrice(price);
        return this;
    }

    public ProductDTOBuilder descriptionInProduct(String description){
        product.setDescription(description);
        return this;
    }

    public ProductDTOBuilder withPromotion(boolean promotion){
        product.setPromotion(promotion);
        return this;
    }

    public ProductDTOBuilder withPercent(float percent){
        product.setPercent(percent);
        return this;
    }

    public ProductDTOBuilder withCategory(String category){
        product.setCategory(category);
        return this;
    }

    public ProductDTOBuilder withQuantity(int quantity){
        product.setQuantity(quantity);
        return this;
    }

    public ProductDTO build(){
        return this.product;
    }
}
