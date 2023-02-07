package be.technifutur.Benjamiches.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serial;

@Embeddable
@Data
public class IngredientInSandwichID implements java.io.Serializable{
    private static final long SerialVersionID = 1L;

    @Column(name = "ingredient_id")
    private long ingredientId;

    @Column(name = "sandwich_id")
    private long sandwichId;
}
