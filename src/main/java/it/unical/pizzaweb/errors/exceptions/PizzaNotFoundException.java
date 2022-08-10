package it.unical.pizzaweb.errors.exceptions;

public class PizzaNotFoundException extends Exception {
    public PizzaNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
