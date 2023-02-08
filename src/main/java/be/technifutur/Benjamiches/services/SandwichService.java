package be.technifutur.Benjamiches.services;

import be.technifutur.Benjamiches.exceptions.RessourceNotFound;
import be.technifutur.Benjamiches.model.dto.SandwichDTO;
import be.technifutur.Benjamiches.model.entities.Ingredient;
import be.technifutur.Benjamiches.model.entities.IngredientsInSandwich;
import be.technifutur.Benjamiches.model.entities.Sandwich;
import be.technifutur.Benjamiches.repositories.IngredientRepository;
import be.technifutur.Benjamiches.repositories.IngredientsInSandwichRepository;
import be.technifutur.Benjamiches.repositories.SandwichRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SandwichService {

    private final SandwichRepository sandwichRepo;
    private final IngredientRepository ingredientRepo;
    private final IngredientsInSandwichRepository ingredientsInSandwichRepo;

    public SandwichService(SandwichRepository sandwichRepo, IngredientRepository ingredientRepo, IngredientsInSandwichRepository ingredientsInSandwitchRepo) {
        this.sandwichRepo = sandwichRepo;
        this.ingredientRepo = ingredientRepo;
        this.ingredientsInSandwichRepo = ingredientsInSandwitchRepo;
    }

    public List<SandwichDTO> getAll() {
        return sandwichRepo.findAll().stream().map(SandwichDTO::from).toList();
    }

    public boolean setIngredientsToSandwich(long sandwichId, Map<Long, Double> ingredientsId) {
        Sandwich sandwich = sandwichRepo.findById(sandwichId)
                .orElseThrow(() -> new RessourceNotFound("Sandwich not found"));

        if (!sandwichRepo.existsById(sandwichId) || ingredientsId.isEmpty()) {
            return false;
        }

        for (Map.Entry<Long, Double> entry : ingredientsId.entrySet()) {
            if (!ingredientRepo.existsById(entry.getKey())) {
                return false;
            }
        }

        if (sandwich.getIngredientsInSandwiches().size() > 0) {
            ingredientsInSandwichRepo.deleteAll(sandwich.getIngredientsInSandwiches());
        }

        for (Map.Entry<Long, Double> entry : ingredientsId.entrySet()) {
            IngredientsInSandwich ingredientsInSandwich = new IngredientsInSandwich();
            Ingredient ingredient = ingredientRepo.findById(entry.getKey()).get();

            ingredientsInSandwich.setSandwich(sandwich);
            ingredientsInSandwich.setIngredient(ingredient);
            ingredientsInSandwich.setQuantity(entry.getValue());
            sandwich.getIngredientsInSandwiches().add(ingredientsInSandwich);

            ingredientsInSandwichRepo.save(ingredientsInSandwich);
        }

        sandwichRepo.save(sandwich);

        return true;
    }

    public List<SandwichDTO> getByDiet(long diet) {
        return sandwichRepo.findByDietId(diet).stream().map(SandwichDTO::from).toList();
    }
}
