package it.unical.pizzaweb.controllers;

import it.unical.pizzaweb.dto.PizzaDTO;
import it.unical.pizzaweb.dto.input.CreatePizzaInputDTO;
import it.unical.pizzaweb.errors.exceptions.IngredientNotFoundException;
import it.unical.pizzaweb.errors.exceptions.PizzaNotFoundException;
import it.unical.pizzaweb.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @PostMapping("/pizza")
    public PizzaDTO createPizza(@RequestBody CreatePizzaInputDTO createPizzaInputDTO) throws IngredientNotFoundException {
        return pizzaService.createPizza(createPizzaInputDTO);
    }

    @GetMapping("/pizza")
    public ResponseEntity<List<PizzaDTO>> getPizzas() {
        return ResponseEntity.ok(pizzaService.getPizzas());
    }

    @GetMapping("/pizza/{id}")
    public ResponseEntity<PizzaDTO> getPizza(@PathVariable Long id) throws PizzaNotFoundException {
        return ResponseEntity.ok(pizzaService.getPizza(id));
    }

    @PutMapping("/pizza/{pizzaName}")
    public ResponseEntity<Boolean> makePizza(@PathVariable String pizzaName) throws IngredientNotFoundException, PizzaNotFoundException {
        return ResponseEntity.ok(pizzaService.makePizza(pizzaName, 1));
    }

}
