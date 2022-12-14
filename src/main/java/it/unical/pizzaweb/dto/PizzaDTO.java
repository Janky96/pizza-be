package it.unical.pizzaweb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PizzaDTO {

    private Long id;
    private String name;
    private Double price;
    private String image;
    private List<String> ingredients;
}
