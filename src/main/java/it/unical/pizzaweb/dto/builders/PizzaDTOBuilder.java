package it.unical.pizzaweb.dto.builders;

import it.unical.pizzaweb.dto.PizzaDTO;
import it.unical.pizzaweb.entities.Ingredient;
import it.unical.pizzaweb.entities.Pizza;

import java.util.stream.Collectors;

public class PizzaDTOBuilder {

    private PizzaDTOBuilder() {
    }

    public static PizzaDTO buildDTOFromPizza(Pizza pizza) {
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(pizza.getId());
        pizzaDTO.setName(pizza.getName());
        pizzaDTO.setPrice(pizza.getPrice());
        pizzaDTO.setIngredients(pizza.getIngredients().stream().map(Ingredient::getName).collect(Collectors.toList()));
        pizzaDTO.setImage(pizza.getImage());
        return pizzaDTO;
    }
}
