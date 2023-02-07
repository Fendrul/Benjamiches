package be.technifutur.Benjamiches.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Long id;


    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart")
    private Set<SandwichesInCart> sandwichesInCarts = new HashSet<>();

//    @MapsId("sandwichId")
//    @ManyToOne
//    @JoinColumn(name = "sandwich_id")
//    private Sandwich sandwich;
}
