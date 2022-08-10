package it.unical.pizzaweb.dto;

import it.unical.pizzaweb.entities.Pizza;
import it.unical.pizzaweb.entities.Purchase;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductInPurchaseDTO {

    private Long id;
    private Pizza pizza;
    private String quantity;
    private Double price;
    private Purchase purchase;

}
