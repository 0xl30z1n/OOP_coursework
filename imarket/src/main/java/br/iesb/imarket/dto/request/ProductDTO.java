package br.iesb.imarket.dto.request;

import br.iesb.imarket.model.Category;

public class ProductDTO {
    private String name;
    private String brand;
    private float price;
    private int quantity;
    private String description;
    private boolean promotion;
    private float percent;
    private Category category;
    public ProductDTO(String name, String brand, float price, int quantity, String description, boolean promotion, float percent, Category category) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.promotion = promotion;
        this.percent = percent;
        this.category = category;
    }

    public ProductDTO(){
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }
}