package it.unical.pizzaweb.services;

import it.unical.pizzaweb.dto.PizzaDTO;
import it.unical.pizzaweb.dto.builders.PizzaDTOBuilder;
import it.unical.pizzaweb.dto.input.CreatePizzaInputDTO;
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

    public List<PizzaDTO> getPizzas() throws PizzaNotFoundException {
        List<Pizza> pizzas = pizzaRepository.findAll();
        if(pizzas.isEmpty()) {
            throw new PizzaNotFoundException("Non esistono pizze nel DB");
        }
        return pizzas.stream().map(PizzaDTOBuilder::buildDTOFromPizza).collect(Collectors.toList());
    }

    public PizzaDTO getPizza(Long id) throws PizzaNotFoundException {
        Optional<Pizza> optionalPizza = (pizzaRepository.findById(id));
        if(optionalPizza.isPresent()) {
            return mapper.map(optionalPizza.get(), PizzaDTO.class);
        }
        throw new PizzaNotFoundException("La pizza richiesta non esiste");
    }

    public PizzaDTO getPizzaByName(String name) throws PizzaNotFoundException {
        Optional<Pizza> optionalPizza = pizzaRepository.findByName(name);
        if(optionalPizza.isPresent()) {
            return mapper.map(optionalPizza.get(), PizzaDTO.class);
        }
        throw new PizzaNotFoundException("La pizza richiesta non esiste");
    }

    @Transactional
    public Boolean makePizza(PizzaDTO pizzaDTO) throws IngredientNotFoundException {
        List<String> ingredients = pizzaDTO.getIngredients();
        for(String i: ingredients) {
            if(ingredientRepository.useIngredient(i, 1) < 1) {
                throw new IngredientNotFoundException(String.format("Ingrediente <%s> finito o assente", i));
            }
        }
        return true;
    }

    @Transactional
    public Boolean makePizza(String pizzaName, Integer quantity) throws IngredientNotFoundException, PizzaNotFoundException {
        Optional<Pizza> optionalPizza = pizzaRepository.findByName(pizzaName);
        if(optionalPizza.isEmpty()) {
            throw new PizzaNotFoundException(String.format("La pizza <%s> non esiste", pizzaName));
        }
        Pizza pizza = optionalPizza.get();
        List<Ingredient> ingredients = pizza.getIngredients();
        for(Ingredient i: ingredients) {
            if(ingredientRepository.useIngredient(i.getName(), quantity != null ? quantity : 1) < 1) {
                throw new IngredientNotFoundException(String.format("Ingrediente <%s> finito o assente", i.getName()));
            }
        }
        return true;
    }

    @Transactional
    public Boolean makePizza(Long id, Integer quantity) throws IngredientNotFoundException, PizzaNotFoundException {
        Optional<Pizza> optionalPizza = pizzaRepository.findById(id);
        if(optionalPizza.isEmpty()) {
            throw new PizzaNotFoundException(String.format("La pizza con id <%d> non esiste", id));
        }
        Pizza pizza = optionalPizza.get();
        List<Ingredient> ingredients = pizza.getIngredients();
        for(Ingredient i: ingredients) {
            if(ingredientRepository.useIngredient(i.getName(), quantity != null ? quantity : 1) < 1) {
                throw new IngredientNotFoundException(String.format("Ingrediente <%s> finito o assente", i.getName()));
            }
        }
        return true;
    }

    @Transactional
    public PizzaDTO createPizza(CreatePizzaInputDTO createPizzaInputDTO) throws IngredientNotFoundException {
        List<String> ingredients = createPizzaInputDTO.getIngredients();
        List<Ingredient> ingredientsInRepository = ingredientRepository.findByNameIn(ingredients);
        if(ingredients.size() != ingredientsInRepository.size()) {
            throw new IngredientNotFoundException("Non sono stati trovati tutti gli ingredienti");
        }
        Pizza pizza = new Pizza();
        pizza.setName(createPizzaInputDTO.getName());
        pizza.setPrice(calculatePrice(ingredientsInRepository));
        pizza.setIngredients(ingredientsInRepository);
        pizzaRepository.save(pizza);
        return mapper.map(pizza, PizzaDTO.class);
    }

    private Double calculatePrice(List<Ingredient> ingredients) {
        return ingredients.stream().map(Ingredient::getPrice).collect(Collectors.toList())
                .stream().reduce(0D, (a, b) -> a + b);
    }
}
