package it.unical.pizzaweb.services;

import it.unical.pizzaweb.dto.IngredientDTO;
import it.unical.pizzaweb.entities.Ingredient;
import it.unical.pizzaweb.repositories.IngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    private final ModelMapper mapper = new ModelMapper();

    public IngredientDTO createIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = mapper.map(ingredientDTO, Ingredient.class);
        if(ingredientRepository.findByName(ingredient.getName()).isPresent()) {
            throw new IllegalArgumentException("Ingrediente esistente");
        }
        ingredientRepository.save(ingredient);
        return mapper.map(ingredient, IngredientDTO.class);
    }

    public IngredientDTO updateIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = mapper.map(ingredientDTO, Ingredient.class);
        ingredientRepository.save(ingredient);
        return mapper.map(ingredient, IngredientDTO.class);
    }

    public List<IngredientDTO> getIngredients() {
        return ingredientRepository.findAll().stream().map(ingredient -> mapper.map(ingredient, IngredientDTO.class))
                .collect(Collectors.toList());
    }

    public IngredientDTO getIngredient(Long id) {
        Optional<Ingredient> ingredientDTOOptional = ingredientRepository.findById(id);
        return mapper.map(ingredientDTOOptional.orElse(null), IngredientDTO.class);
    }

    public IngredientDTO getIngredientByName(String name) {
        Optional<Ingredient> ingredientDTOOptional = ingredientRepository.findByName(name);
        return mapper.map(ingredientDTOOptional.orElse(null), IngredientDTO.class);
    }

    public Boolean deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
