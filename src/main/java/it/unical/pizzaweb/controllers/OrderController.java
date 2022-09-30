package it.unical.pizzaweb.controllers;

import it.unical.pizzaweb.dto.input.OrderInputDTO;
import it.unical.pizzaweb.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/order")
    public void makeOrder(List<OrderInputDTO> orderInputDTOList) {
        purchaseService.makeOrder(orderInputDTOList);
    }
}
