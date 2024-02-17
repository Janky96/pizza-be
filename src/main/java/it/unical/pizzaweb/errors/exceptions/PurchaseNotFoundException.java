package it.unical.pizzaweb.errors.exceptions;

public class PurchaseNotFoundException extends Exception {
    public PurchaseNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
