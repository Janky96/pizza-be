package it.unical.pizzaweb.facades;

import it.unical.pizzaweb.authentication.repositories.UserRepository;
import it.unical.pizzaweb.dto.input.OrderPizzaInputDTO;
import it.unical.pizzaweb.entities.ProductInPurchase;
import it.unical.pizzaweb.entities.Purchase;
import it.unical.pizzaweb.errors.exceptions.IngredientNotFoundException;
import it.unical.pizzaweb.errors.exceptions.PizzaNotFoundException;
import it.unical.pizzaweb.repositories.PurchaseRepository;
import it.unical.pizzaweb.services.PizzaService;
import it.unical.pizzaweb.services.ProductInPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class OrderFacade {

    private final ProductInPurchaseService productInPurchaseService;
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final PizzaService pizzaService;

    @Autowired
    public OrderFacade(ProductInPurchaseService productInPurchaseService, PurchaseRepository purchaseRepository, UserRepository userRepository, PizzaService pizzaService) {
        this.productInPurchaseService = productInPurchaseService;
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.pizzaService = pizzaService;
    }

    public void makeOrder(String usernameBuyer, List<OrderPizzaInputDTO> orderPizzaInputDTOList) throws IngredientNotFoundException, PizzaNotFoundException {
        Purchase purchase = new Purchase();
        purchase.setDate(new Date());
        purchase.setBuyer(userRepository.findByUsername(usernameBuyer));
        purchaseRepository.save(purchase);

        List<ProductInPurchase> productInPurchaseList = new LinkedList<>();
        purchase.setProductInPurchaseList(productInPurchaseList);
        for(OrderPizzaInputDTO orderPizzaInputDTO : orderPizzaInputDTOList) {
            Long idPizza = orderPizzaInputDTO.getId();
            Integer quantityPizza = orderPizzaInputDTO.getQuantity();
            pizzaService.makePizza(idPizza, quantityPizza);
            productInPurchaseList.add(productInPurchaseService.makeProductInPurchase(idPizza, quantityPizza, purchase));
        }
        purchaseRepository.save(purchase);
    }
}
