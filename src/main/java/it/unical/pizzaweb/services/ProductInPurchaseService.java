package it.unical.pizzaweb.services;

import it.unical.pizzaweb.dto.ProductInPurchaseDTO;
import it.unical.pizzaweb.entities.Pizza;
import it.unical.pizzaweb.entities.ProductInPurchase;
import it.unical.pizzaweb.entities.Purchase;
import it.unical.pizzaweb.repositories.PizzaRepository;
import it.unical.pizzaweb.repositories.ProductInPurchaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductInPurchaseService {

    @Autowired
    ProductInPurchaseRepository productInPurchaseRepository;

    @Autowired
    PizzaRepository pizzaRepository;

    private final ModelMapper mapper = new ModelMapper();

    public List<ProductInPurchaseDTO> getProductsInPurchase() {
        return productInPurchaseRepository.findAll().stream()
                .map(productInPurchase -> mapper.map(productInPurchase, ProductInPurchaseDTO.class))
                .collect(Collectors.toList());
    }

    public ProductInPurchaseDTO getProductInPurchase(Long id) {
        return mapper.map(productInPurchaseRepository.findById(id), ProductInPurchaseDTO.class);
    }

    @Transactional
    public ProductInPurchase makeProductInPurchase(Long idPizza, Integer quantity, Purchase purchase) {
        ProductInPurchase productInPurchase = new ProductInPurchase();
        productInPurchase.setPurchase(purchase);
        Pizza pizza = mapper.map(pizzaRepository.findById(idPizza), Pizza.class);
        productInPurchase.setQuantity(quantity);
        productInPurchase.setPrice(pizza.getPrice() * quantity);
        productInPurchase.setPizza(pizza);
        productInPurchaseRepository.save(productInPurchase);

        return productInPurchase;
    }

}
