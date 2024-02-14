package it.unical.pizzaweb.services;

import it.unical.pizzaweb.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

//    public List<PurchaseDTO> getPurchasesByBuyer(String username) {
//
//    }

}
