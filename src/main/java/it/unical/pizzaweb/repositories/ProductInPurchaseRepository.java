package it.unical.pizzaweb.repositories;

import it.unical.pizzaweb.entities.ProductInPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInPurchaseRepository extends JpaRepository<ProductInPurchase, Long> {
}
