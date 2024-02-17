package it.unical.pizzaweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unical.pizzaweb.entities.Pizza;
import it.unical.pizzaweb.entities.Purchase;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductInPurchaseDTO {

    private Long id;
    private Pizza pizza;
    private Integer quantity;
    private Double price;
    @JsonIgnore
    private Purchase purchase;

}
