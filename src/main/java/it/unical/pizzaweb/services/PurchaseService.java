package it.unical.pizzaweb.services;

import it.unical.pizzaweb.dto.input.OrderInputDTO;
import it.unical.pizzaweb.entities.Purchase;
import it.unical.pizzaweb.repositories.PurchaseRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    ProductInPurchaseService productInPurchaseService;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    public void makeOrder(List<OrderInputDTO> orderInputDTOList) {
        Purchase purchase = new Purchase();
        for(OrderInputDTO orderInputDTO: orderInputDTOList) {
            productInPurchaseService.makeProductInPurchase(orderInputDTO.getPizzaName(), orderInputDTO.getQuantity(), purchase);
        }
    }
}
