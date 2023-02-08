package be.technifutur.Benjamiches.repositories;

import be.technifutur.Benjamiches.model.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
