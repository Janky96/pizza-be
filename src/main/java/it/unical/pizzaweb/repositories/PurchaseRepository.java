package it.unical.pizzaweb.repositories;

import it.unical.pizzaweb.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByBuyerId(Long id);
}
