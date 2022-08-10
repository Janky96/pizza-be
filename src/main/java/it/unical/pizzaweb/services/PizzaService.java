package it.unical.pizzaweb.services;

import it.unical.pizzaweb.dto.IngredientDTO;
import it.unical.pizzaweb.dto.PizzaDTO;
import it.unical.pizzaweb.entities.Ingredient;
import it.unical.pizzaweb.entities.Pizza;
import it.unical.pizzaweb.errors.exceptions.IngredientNotFoundException;
import it.unical.pizzaweb.errors.exceptions.PizzaNotFoundException;
import it.unical.pizzaweb.repositories.IngredientRepository;
import it.unical.pizzaweb.repositories.PizzaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    private final ModelMapper mapper = new ModelMapper();

    public List<PizzaDTO> getPizzas() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas.stream().map(pizza -> mapper.map(pizza, PizzaDTO.class)).collect(Collectors.toList());
    }

    public PizzaDTO getPizza(Long id) throws PizzaNotFoundException {
        Optional<Pizza> optionalPizza = (pizzaRepository.findById(id));
        if(optionalPizza.isPresent()) {
            return mapper.map(optionalPizza.get(), PizzaDTO.class);
        }
        throw new PizzaNotFoundException("La pizza richiesta non esiste");
    }

    @Transactional
    public Boolean makePizza(PizzaDTO pizzaDTO) throws IngredientNotFoundException {
        List<IngredientDTO> ingredients = pizzaDTO.getIngredients();
        for(IngredientDTO i: ingredients) {
            if(ingredientRepository.useIngredient(i.getName()) < 1) {
                throw new IngredientNotFoundException(String.format("Ingrediente <%s> finito o assente", i.getName()));
            }
        }
        return true;
    }

    @Transactional
    public Boolean makePizza(String pizzaName) throws IngredientNotFoundException, PizzaNotFoundException {
        Optional<Pizza> optionalPizza = pizzaRepository.findByName(pizzaName);
        if(optionalPizza.isEmpty()) {
            throw new PizzaNotFoundException("La pizza <%s> non esiste");
        }
        Pizza pizza = optionalPizza.get();
        List<Ingredient> ingredients = pizza.getIngredients();
        for(Ingredient i: ingredients) {
            if(ingredientRepository.useIngredient(i.getName()) < 1) {
                throw new IngredientNotFoundException(String.format("Ingrediente <%s> finito o assente", i.getName()));
            }
        }
        return true;
    }

    @Transactional
    public PizzaDTO createPizza(PizzaDTO pizzaDTO) throws IngredientNotFoundException {
        List<IngredientDTO> ingredients = pizzaDTO.getIngredients();
        List<Ingredient> ingredientsInRepository = ingredientRepository.findByNameIn(ingredients.stream()
                .map(IngredientDTO::getName).collect(Collectors.toList()));
        if(ingredients.size() != ingredientsInRepository.size()) {
            ingredients.removeAll(ingredientsInRepository);
        }
        Pizza pizza = pizzaRepository.save(mapper.map(pizzaDTO, Pizza.class));
        return mapper.map(pizza, PizzaDTO.class);
    }
}
