package br.iesb.imarket.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopcartEntity {

    private List<ShopcartItemEntity> items;

    private float total;

    private Date lastModification;

    public ShopcartEntity(List<ShopcartItemEntity> items, float total, Date lastModification) {
        this.items = items;
        this.total = total;
        this.lastModification = lastModification;
    }

    public ShopcartEntity(){
    }

    public List<ShopcartItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ShopcartItemEntity> items) {
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