package it.unical.pizzaweb.controllers;

import it.unical.pizzaweb.authentication.config.JwtTokenUtil;
import it.unical.pizzaweb.dto.PurchaseDTO;
import it.unical.pizzaweb.dto.input.OrderPizzaInputDTO;
import it.unical.pizzaweb.errors.exceptions.IngredientNotFoundException;
import it.unical.pizzaweb.errors.exceptions.PizzaNotFoundException;
import it.unical.pizzaweb.errors.exceptions.PurchaseNotFoundException;
import it.unical.pizzaweb.facades.OrderFacade;
import it.unical.pizzaweb.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderFacade orderFacade;
    private final PurchaseService purchaseService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public OrderController(OrderFacade orderFacade, PurchaseService purchaseService, JwtTokenUtil jwtTokenUtil) {
        this.orderFacade = orderFacade;
        this.purchaseService = purchaseService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/orders/user")
    public List<PurchaseDTO> getOrdersOfUser(@RequestHeader("Authorization") String token) throws PurchaseNotFoundException {
        return purchaseService.getPurchasesByBuyer(jwtTokenUtil.getUsernameFromToken(token.replace("Bearer ", "")));
    }

    @PostMapping("/orders")
    public void makeOrder(@RequestHeader("Authorization") String token, @RequestBody List<OrderPizzaInputDTO> orderPizzaInputDTOList) throws IngredientNotFoundException, PizzaNotFoundException {
        orderFacade.makeOrder(jwtTokenUtil.getUsernameFromToken(token.replace("Bearer ", "")), orderPizzaInputDTOList);
    }
}
