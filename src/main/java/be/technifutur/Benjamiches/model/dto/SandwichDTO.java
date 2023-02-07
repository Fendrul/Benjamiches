package be.technifutur.Benjamiches.model.dto;

import be.technifutur.Benjamiches.model.entities.Sandwich;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
public class SandwichDTO {

    private String name;
    private String description;
    private double price;
    private IngredientsDTO ingredientsDTO;


    @Getter
    @Setter
    @Builder
    private static class IngredientsDTO {
        Map<String, Map.Entry<Double, String>> ingredients; //Get the name, the quantity and the unit of the ingredient
    }

    public static SandwichDTO from(Sandwich entity) {
        if (entity == null)
            return null;

        return SandwichDTO.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .ingredientsDTO(
                        IngredientsDTO.builder()
                                .ingredients(new HashMap<>(
                                        entity.getIngredientsInSandwiches().stream()
                                                .collect(
                                                        HashMap::new,
                                                        (map, ingredient) -> map.put(
                                                                ingredient.getIngredient().getName(),
                                                                Map.entry(
                                                                        ingredient.getQuantity(),
                                                                        ingredient.getIngredient().getUnit().toString()
                                                                )
                                                        ),
                                                        HashMap::putAll
                                                )
                                ))
                                .build()
                )
                .build();
    }
}
