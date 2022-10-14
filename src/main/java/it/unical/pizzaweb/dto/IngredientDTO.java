package it.unical.pizzaweb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class IngredientDTO {

    private Long id;
    private String name;
    private Double price;
    private Integer quantity;

}
