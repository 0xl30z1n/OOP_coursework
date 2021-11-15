package br.iesb.imarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "percent", nullable = false)
    private float percent;

    @Column(name = "create", nullable = true)
    private Date creationDate;

    @Column(name = "update", nullable = true)
    private Date updatingDate;

    @Column(name = "promotion", nullable = false)
    private boolean promotion;

    @Column(name = "category", nullable = false)
    private String category;

    public float getPriceDto() {
        if(this.promotion == true){
            return price - (price * percent);
        }
        return price;
    }
}