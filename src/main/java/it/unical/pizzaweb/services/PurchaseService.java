package it.unical.pizzaweb.services;

import it.unical.pizzaweb.dto.input.OrderPizzaInputDTO;
import it.unical.pizzaweb.entities.Purchase;
import it.unical.pizzaweb.errors.exceptions.IngredientNotFoundException;
import it.unical.pizzaweb.errors.exceptions.PizzaNotFoundException;
import it.unical.pizzaweb.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    ProductInPurchaseService productInPurchaseService;

    @Autowired
    PurchaseRepository purchaseRepository;


    public void makeOrder(List<OrderPizzaInputDTO> orderPizzaInputDTOList) throws IngredientNotFoundException, PizzaNotFoundException {
        Purchase purchase = new Purchase();
        for(OrderPizzaInputDTO orderPizzaInputDTO : orderPizzaInputDTOList) {
            productInPurchaseService.makeProductInPurchase(orderPizzaInputDTO.getPizzaName(), orderPizzaInputDTO.getQuantity(), purchase);
        }
    }
}
