package be.technifutur.Benjamiches.repositories;

import be.technifutur.Benjamiches.model.entities.IngredientsInSandwich;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsInSandwichRepository extends JpaRepository<IngredientsInSandwich, Long> {
}
