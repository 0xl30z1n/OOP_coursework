package br.iesb.imarket.model;

import java.util.Date;
import java.util.List;

public class ShopCart {

    private List<ShopCartItem> items;

    private float total;

    private Date lastModification;

    public ShopCart(List<ShopCartItem> items, float total, Date lastModification) {
        this.items = items;
        this.total = total;
        this.lastModification = lastModification;
    }

    public ShopCart(){
    }

    public List<ShopCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShopCartItem> items) {
        this.items = items;
    }

    public float getTotalToPay() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }

    public int quantityItems(){
        return items.size();
    }

}