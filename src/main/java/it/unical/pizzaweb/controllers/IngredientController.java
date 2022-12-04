package it.unical.pizzaweb.controllers;

import it.unical.pizzaweb.dto.IngredientDTO;
import it.unical.pizzaweb.errors.exceptions.IngredientNotFoundException;
import it.unical.pizzaweb.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> getIngredients() {
        return ResponseEntity.ok(ingredientService.getIngredients());
    }

    @GetMapping("/name/{ingredientName}")
    public ResponseEntity<IngredientDTO> getIngredientByName(@PathVariable String ingredientName) {
        return ResponseEntity.ok(ingredientService.getIngredientByName(ingredientName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> getIngredientById(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }

    @PostMapping
    public ResponseEntity<IngredientDTO> createIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return ResponseEntity.ok(ingredientService.createIngredient(ingredientDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientDTO> updateIngredient(@PathVariable Long id, IngredientDTO ingredientDTO) throws IngredientNotFoundException {
        return ResponseEntity.ok(ingredientService.updateIngredient(id, ingredientDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteIngredient(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.deleteIngredient(id));
    }

}
