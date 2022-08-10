package it.unical.pizzaweb.repositories;

import it.unical.pizzaweb.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Optional<Pizza> findByName(String name);

    List<Pizza> findByNameIn(List<String> names);
}
