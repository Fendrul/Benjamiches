package be.technifutur.Benjamiches.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sandwiches_in_cart")
public class SandwichesInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sandwich_in_cart_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "sandwich_id", nullable = false)
    private Sandwich sandwich;

    private int quantity;

}
