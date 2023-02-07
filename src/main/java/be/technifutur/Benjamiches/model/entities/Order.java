package be.technifutur.Benjamiches.model.entities;

import be.technifutur.Benjamiches.model.entities.enums.OrderStates;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Column(name = "date_order",nullable = false)
    private LocalDate dateOrder;

    @Column(name = "date_delivery",nullable = false)
    private LocalDate dateDelivery;

    @Column(nullable = false)
    private double discount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStates status;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "orders")
    private List<Sandwich> sandwiches;


}
