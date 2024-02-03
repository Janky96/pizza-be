package it.unical.pizzaweb.services;

import it.unical.pizzaweb.dto.ProductInPurchaseDTO;
import it.unical.pizzaweb.dto.input.OrderPizzaInputDTO;
import it.unical.pizzaweb.entities.Pizza;
import it.unical.pizzaweb.entities.ProductInPurchase;
import it.unical.pizzaweb.entities.Purchase;
import it.unical.pizzaweb.errors.exceptions.IngredientNotFoundException;
import it.unical.pizzaweb.errors.exceptions.PizzaNotFoundException;
import it.unical.pizzaweb.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    ProductInPurchaseService productInPurchaseService;

    @Autowired
    PurchaseRepository purchaseRepository;


    public void makeOrder(List<OrderPizzaInputDTO> orderPizzaInputDTOList) throws IngredientNotFoundException, PizzaNotFoundException {
        Purchase purchase = new Purchase();
        List<ProductInPurchaseDTO> productInPurchaseDTOList = new ArrayList<>();
        for(OrderPizzaInputDTO orderPizzaInputDTO : orderPizzaInputDTOList) {
            productInPurchaseDTOList.add(productInPurchaseService.makeProductInPurchase(orderPizzaInputDTO.getId(), orderPizzaInputDTO.getQuantity(), purchase));
        }
    }
}
