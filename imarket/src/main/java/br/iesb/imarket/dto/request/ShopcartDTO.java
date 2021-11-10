package br.iesb.imarket.dto.request;

import br.iesb.imarket.model.ShopCartItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopcartDTO {

    private List<ShopCartItem> items = new ArrayList<>();

    private float total;

    private Date lastModification;

    public ShopcartDTO(List<ShopCartItem> items, float total, Date lastModification) {
        this.items = items;
        this.total = total;
        this.lastModification = lastModification;
    }

    public ShopcartDTO(){
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