package it.unical.pizzaweb.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@Data
public class CreatePizzaInputDTO {
    private String name;
    private List<String> ingredients;
}
