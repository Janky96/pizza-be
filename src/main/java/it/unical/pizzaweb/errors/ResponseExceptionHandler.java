package it.unical.pizzaweb.errors;

import it.unical.pizzaweb.errors.exceptions.IngredientNotFoundException;
import it.unical.pizzaweb.errors.exceptions.PizzaNotFoundException;
import it.unical.pizzaweb.errors.exceptions.PurchaseNotFoundException;
import it.unical.pizzaweb.errors.exceptions.UsernameAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public final ResponseEntity<ErrorMessage> handleUsernameAlreadyExistsExceptio(UsernameAlreadyExistsException ex) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PurchaseNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handlePurchaseNotFoundException(PurchaseNotFoundException ex) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PizzaNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handlePurchaseNotFoundException(PizzaNotFoundException ex) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IngredientNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleIngredientNotFoundException(IngredientNotFoundException ex) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex) {
        ErrorMessage errorObj = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
