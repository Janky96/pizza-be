package it.unical.pizzaweb.services;

import it.unical.pizzaweb.authentication.repositories.UserRepository;
import it.unical.pizzaweb.dto.PurchaseDTO;
import it.unical.pizzaweb.entities.Purchase;
import it.unical.pizzaweb.errors.exceptions.PurchaseNotFoundException;
import it.unical.pizzaweb.repositories.PurchaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
    }

    public List<PurchaseDTO> getPurchasesByBuyer(String username) throws PurchaseNotFoundException {
        List<Purchase> purchases = purchaseRepository.findByBuyerId(userRepository.findByUsername(username).getId());
        if(purchases == null || purchases.isEmpty()) {
            throw new PurchaseNotFoundException("Non trovati ordini per la user: " + username);
        }
        ModelMapper modelMapper = new ModelMapper();
        return List.of(modelMapper.map(purchases, PurchaseDTO[].class));
    }

}
