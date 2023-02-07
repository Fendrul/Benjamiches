package be.technifutur.Benjamiches.repositories;

import be.technifutur.Benjamiches.model.entities.Sandwich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SandwichRepository extends JpaRepository<Sandwich, Long> {

    @Override
    @Query("SELECT s FROM Sandwich s LEFT JOIN FETCH s.ingredientsInSandwiches WHERE s.id = ?1")
    Optional<Sandwich> findById(Long aLong);

    @Query("SELECT s FROM Sandwich s LEFT JOIN FETCH s.ingredientsInSandwiches WHERE s.name = ?1")
    Optional<Sandwich> findByName(String name);
}
