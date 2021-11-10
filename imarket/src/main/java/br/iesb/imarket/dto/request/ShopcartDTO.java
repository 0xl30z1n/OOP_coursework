package br.iesb.imarket.dto.request;

import br.iesb.imarket.model.ShopcartItemEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopcartDTO {

    private List<ShopcartItemEntity> items = new ArrayList<>();

    private float total;

    private Date lastModification;

    public ShopcartDTO(List<ShopcartItemEntity> items, float total, Date lastModification) {
        this.items = items;
        this.total = total;
        this.lastModification = lastModification;
    }

    public ShopcartDTO(){
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