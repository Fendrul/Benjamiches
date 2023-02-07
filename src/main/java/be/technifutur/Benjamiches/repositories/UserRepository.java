package be.technifutur.Benjamiches.repositories;

import be.technifutur.Benjamiches.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
}
