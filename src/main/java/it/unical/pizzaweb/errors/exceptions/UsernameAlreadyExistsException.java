package it.unical.pizzaweb.errors.exceptions;

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
