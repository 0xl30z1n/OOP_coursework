package br.com.market.imarket.dto;


public class OrderDTO {
    private Long id;
    private float value;

    public OrderDTO(Long id, float value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

}