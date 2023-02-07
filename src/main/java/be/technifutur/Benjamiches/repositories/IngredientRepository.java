package be.technifutur.Benjamiches.repositories;

import be.technifutur.Benjamiches.model.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("SELECT i FROM Ingredient i WHERE i.name = ?1")
    Optional<Ingredient> findByName(String ingredient);
}
