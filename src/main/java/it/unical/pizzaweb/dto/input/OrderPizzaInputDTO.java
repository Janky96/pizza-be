package it.unical.pizzaweb.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderPizzaInputDTO {

    private Long id;
    private int quantity;
}
