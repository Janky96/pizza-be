package it.unical.pizzaweb.repositories;

import it.unical.pizzaweb.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Modifying
    @Query(
            value = "UPDATE ingredient i " +
                    "SET quantity = (quantity - :quantity) " +
                    "WHERE i.name = :name",
            nativeQuery = true)
    Integer useIngredient(@Param("name") String name, @Param("quantity") Integer quantity);

    @Modifying
    @Query(
            value = "UPDATE ingredient i " +
                    "SET quantity = (quantity - :quantity) " +
                    "WHERE i.id IN (" +
                        "SELECT pizz_ing.ingredient_id " +
                        "FROM pizza_ingredient pizz_ing " +
                        "INNER JOIN pizza p ON pizz_ing.pizza_id = p.id " +
                        "WHERE p.id = :idPizza" +
                    ")",
            nativeQuery = true
    )
    Integer useIngredientsOfPizzaWithQuantity(@Param("idPizza") Long idPizza, @Param("quantity") Integer quantity);

    Optional<Ingredient> findByName(String name);

    List<Ingredient> findByNameIn(List<String> names);

    void deleteById(Long id);
}
