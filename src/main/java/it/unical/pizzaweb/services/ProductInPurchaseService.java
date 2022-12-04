package it.unical.pizzaweb.services;

import it.unical.pizzaweb.dto.ProductInPurchaseDTO;
import it.unical.pizzaweb.entities.Pizza;
import it.unical.pizzaweb.entities.ProductInPurchase;
import it.unical.pizzaweb.entities.Purchase;
import it.unical.pizzaweb.errors.exceptions.IngredientNotFoundException;
import it.unical.pizzaweb.errors.exceptions.PizzaNotFoundException;
import it.unical.pizzaweb.repositories.ProductInPurchaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductInPurchaseService {

    @Autowired
    ProductInPurchaseRepository productInPurchaseRepository;

    @Autowired
    PizzaService pizzaService;

    private final ModelMapper mapper = new ModelMapper();

    public List<ProductInPurchaseDTO> getProductsInPurchase() {
        return productInPurchaseRepository.findAll().stream()
                .map(productInPurchase -> mapper.map(productInPurchase, ProductInPurchaseDTO.class))
                .collect(Collectors.toList());
    }

    public ProductInPurchaseDTO getProductInPurchase(Long id) {
        return mapper.map(productInPurchaseRepository.findById(id), ProductInPurchaseDTO.class);
    }

    public ProductInPurchaseDTO makeProductInPurchase(Long idPizza, Integer quantity, Purchase purchase) throws IngredientNotFoundException, PizzaNotFoundException {
        ProductInPurchaseDTO productInPurchaseDTO = new ProductInPurchaseDTO();
        productInPurchaseDTO.setPurchase(purchase);
        pizzaService.makePizza(idPizza, quantity);
        Pizza pizza = mapper.map(pizzaService.getPizza(idPizza), Pizza.class);
        productInPurchaseDTO.setPrice(pizza.getPrice() * quantity);
        productInPurchaseDTO.setPizza(pizza);
        productInPurchaseRepository.save(mapper.map(productInPurchaseDTO, ProductInPurchase.class));

        return productInPurchaseDTO;
    }

}
