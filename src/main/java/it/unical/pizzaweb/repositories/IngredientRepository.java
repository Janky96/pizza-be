package it.unical.pizzaweb.repositories;

import it.unical.pizzaweb.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query(
            value = "UPDATE INGREDIENT I" +
                    "SET QUANTITY = (QUANTITY - :quantity)" +
                    "WHERE I.NAME = :name",
            nativeQuery = true)
    Integer useIngredient(@Param("name") String name, @Param("quantity") Integer quantity);

    Optional<Ingredient> findByName(String name);

    List<Ingredient> findByNameIn(List<String> names);

    void deleteById(Long id);
}
