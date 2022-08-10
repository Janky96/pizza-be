package it.unical.pizzaweb.errors.exceptions;

public class IngredientNotFoundException extends Exception {
    public IngredientNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
