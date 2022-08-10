package it.unical.pizzaweb.services;

import it.unical.pizzaweb.dto.ProductInPurchaseDTO;
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

}
