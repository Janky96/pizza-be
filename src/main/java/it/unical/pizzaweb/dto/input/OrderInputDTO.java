package it.unical.pizzaweb.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderInputDTO {

    private String pizzaName;
    private int quantity;

}
