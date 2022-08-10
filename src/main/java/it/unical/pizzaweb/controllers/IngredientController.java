package it.unical.pizzaweb.controllers;

import it.unical.pizzaweb.dto.IngredientDTO;
import it.unical.pizzaweb.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @GetMapping("/ingredient")
    public ResponseEntity<List<IngredientDTO>> getIngredients() {
        return ResponseEntity.ok(ingredientService.getIngredients());
    }

    @GetMapping("/ingredient/{ingredientName}")
    public ResponseEntity<IngredientDTO> getIngredientByName(@PathVariable String ingredientName) {
        return ResponseEntity.ok(ingredientService.getIngredientByName(ingredientName));
    }

    @GetMapping("/ingredient/{id}")
    public ResponseEntity<IngredientDTO> getIngredientByName(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.getIngredient(id));
    }

    @PostMapping("/ingredient")
    public ResponseEntity<IngredientDTO> createIngredient(IngredientDTO ingredientDTO) {
        return ResponseEntity.ok(ingredientService.createIngredient(ingredientDTO));
    }

    @PutMapping("/ingredient")
    public ResponseEntity<IngredientDTO> updateIngredient(IngredientDTO ingredientDTO) {
        return ResponseEntity.ok(ingredientService.updateIngredient(ingredientDTO));
    }

    @DeleteMapping("/ingredient/{id}")
    public ResponseEntity<Boolean> deleteIngredient(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.deleteIngredient(id));
    }

}
