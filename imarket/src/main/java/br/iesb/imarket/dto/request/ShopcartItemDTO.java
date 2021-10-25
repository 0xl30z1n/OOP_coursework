package br.iesb.imarket.dto.request;

public class ShopcartItemDTO {
    private long id;

    private int quantity;

    public ShopcartItemDTO(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public ShopcartItemDTO(){
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
