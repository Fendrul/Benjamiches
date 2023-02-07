package be.technifutur.Benjamiches.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "sandwich")
public class Sandwich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sandwich_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @ManyToMany
    @JoinTable(name = "sandwich_orders",
            joinColumns = @JoinColumn(name = "sandwich_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;

    @OneToMany(mappedBy = "sandwich")
    private List<SandwichesInCart> sandwichesInCarts;

    @OneToMany(mappedBy = "sandwich")
    private Set<IngredientsInSandwich> ingredientsInSandwiches = new HashSet<>();

//    @MapsId("cartId")
//    @ManyToOne
//    @JoinColumn(name = "cart_id")
//    private Cart cart;

    @ManyToMany(mappedBy = "sandwiches")
    private List<Diet> diets = new ArrayList<>();
}
