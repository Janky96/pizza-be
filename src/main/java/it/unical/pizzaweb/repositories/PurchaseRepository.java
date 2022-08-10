package it.unical.pizzaweb.repositories;

import it.unical.pizzaweb.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
