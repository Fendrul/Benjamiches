package be.technifutur.Benjamiches.model.entities;

import be.technifutur.Benjamiches.model.entities.enums.Units;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ingredients_in_sandwich")
public class IngredientsInSandwich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_in_sandwich_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sandwich_id")
    private Sandwich sandwich;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "quantity", nullable = false)
    private double quantity;
}
