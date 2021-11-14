package br.iesb.imarket.dto.request;

import br.iesb.imarket.enums.CategoryType;
import br.iesb.imarket.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String brand;
    private float price;
    private int quantity;
    private String description;
    private boolean promotion;
    private float percent;
    private CategoryType category;
}