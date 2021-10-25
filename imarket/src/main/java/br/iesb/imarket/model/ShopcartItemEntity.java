package br.iesb.imarket.model;

public class ShopcartItemEntity {

    private long id;

    private int quantity;

    public ShopcartItemEntity(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public ShopcartItemEntity(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
