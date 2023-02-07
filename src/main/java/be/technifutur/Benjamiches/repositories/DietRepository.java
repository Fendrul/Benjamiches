package be.technifutur.Benjamiches.repositories;

import be.technifutur.Benjamiches.model.entities.Diet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DietRepository extends JpaRepository<Diet, Long> {

    //create the findByName method

    @Query("SELECT d FROM Diet d LEFT JOIN FETCH d.sandwiches WHERE d.name = ?1")
    Optional<Diet> findByName(String name);
}
