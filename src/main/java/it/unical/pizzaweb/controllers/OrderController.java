package it.unical.pizzaweb.controllers;

import it.unical.pizzaweb.dto.input.OrderPizzaInputDTO;
import it.unical.pizzaweb.errors.exceptions.IngredientNotFoundException;
import it.unical.pizzaweb.errors.exceptions.PizzaNotFoundException;
import it.unical.pizzaweb.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/order")
    public void makeOrder(List<OrderPizzaInputDTO> orderPizzaInputDTOList) throws IngredientNotFoundException, PizzaNotFoundException {
        purchaseService.makeOrder(orderPizzaInputDTOList);
    }
}
