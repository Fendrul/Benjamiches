package be.technifutur.Benjamiches.utils;

import be.technifutur.Benjamiches.exceptions.RessourceNotFound;
import be.technifutur.Benjamiches.model.entities.*;
import be.technifutur.Benjamiches.model.entities.enums.Units;
import be.technifutur.Benjamiches.repositories.DietRepository;
import be.technifutur.Benjamiches.repositories.IngredientRepository;
import be.technifutur.Benjamiches.repositories.SandwichRepository;
import be.technifutur.Benjamiches.repositories.UserRepository;
import be.technifutur.Benjamiches.services.SandwichService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataInit implements InitializingBean {
    private final SandwichRepository sandwichRepo;
    private final UserRepository userRepo;
    private final DietRepository dietRepo;
    private final IngredientRepository ingredientRepo;
    private final SandwichService sandwichServ;

    public DataInit(SandwichRepository sandwichRepo, UserRepository userRepo, DietRepository dietRepo, IngredientRepository ingredientRepo, SandwichService sandwichServ) {
        this.sandwichRepo = sandwichRepo;
        this.userRepo = userRepo;
        this.dietRepo = dietRepo;
        this.ingredientRepo = ingredientRepo;
        this.sandwichServ = sandwichServ;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //add some ingredients
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Pain");
        ingredient.setUnit(Units.pièce);
        ingredientRepo.save(ingredient);

        ingredient = new Ingredient();
        ingredient.setName("Pain sans gluten");
        ingredient.setUnit(Units.pièce);
        ingredientRepo.save(ingredient);

        ingredient = new Ingredient();
        ingredient.setName("Jambon");
        ingredient.setUnit(Units.gramme);
        ingredientRepo.save(ingredient);

        ingredient = new Ingredient();
        ingredient.setName("Fromage");
        ingredient.setUnit(Units.gramme);
        ingredientRepo.save(ingredient);

        ingredient = new Ingredient();
        ingredient.setName("Thon");
        ingredient.setUnit(Units.gramme);
        ingredientRepo.save(ingredient);

        ingredient = new Ingredient();
        ingredient.setName("Mayo");
        ingredient.setUnit(Units.càs);
        ingredientRepo.save(ingredient);

        ingredient = new Ingredient();
        ingredient.setName("Curry");
        ingredient.setUnit(Units.càc);
        ingredientRepo.save(ingredient);

        ingredient = new Ingredient();
        ingredient.setName("Poulet");
        ingredient.setUnit(Units.gramme);
        ingredientRepo.save(ingredient);

        ingredient = new Ingredient();
        ingredient.setName("Tomate");
        ingredient.setUnit(Units.pièce);
        ingredientRepo.save(ingredient);

        ingredient = new Ingredient();
        ingredient.setName("Oignon");
        ingredient.setUnit(Units.pièce);
        ingredientRepo.save(ingredient);

        //add some diets
        Diet diet = new Diet();
        diet.setName("Vegan");
        diet.setDescription("Sans viande");
        dietRepo.save(diet);

        diet = new Diet();
        diet.setName("Sans gluten");
        diet.setDescription("Sans gluten");
        dietRepo.save(diet);

        //add some sandwiches
        Map<Long, Double> ingredients;
        Sandwich sandwich = new Sandwich();
        sandwich.setName("Thon mayo");

        sandwich.setPrice(5.0);
        sandwich.setDescription("Sandwich au thon et à la mayo");
        sandwichRepo.save(sandwich);
        ingredients = new HashMap<>(
                Map.of(
                        ingredientRepo.findByName("Pain").get().getId(), 1.0,
                        ingredientRepo.findByName("Thon").get().getId(), 750.0,
                        ingredientRepo.findByName("Mayo").get().getId(), 2.0
                )
        );
        sandwichServ.setIngredientsToSandwich(sandwich.getId(), ingredients);

        sandwich = new Sandwich();
        sandwich.setName("Jambon fromage");
        sandwich.setPrice(4.0);
        sandwich.setDescription("Sandwich au jambon et au fromage");
        sandwichRepo.save(sandwich);
        ingredients = new HashMap<>(
                Map.of(
                        ingredientRepo.findByName("Pain").get().getId(), 1.0,
                        ingredientRepo.findByName("Jambon").get().getId(), 250.0,
                        ingredientRepo.findByName("Fromage").get().getId(), 100.0
                )
        );
        sandwichServ.setIngredientsToSandwich(sandwich.getId(), ingredients);

        sandwich = new Sandwich();
        sandwich.setName("Poulet curry");
        sandwich.setPrice(6.0);
        sandwich.setDescription("Sandwich au poulet et au curry");
        sandwichRepo.save(sandwich);
        ingredients = new HashMap<>(
                Map.of(
                        ingredientRepo.findByName("Pain").get().getId(), 1.0,
                        ingredientRepo.findByName("Poulet").get().getId(), 100.0,
                        ingredientRepo.findByName("Curry").get().getId(), 2.0
                )
        );
        sandwichServ.setIngredientsToSandwich(sandwich.getId(), ingredients);

        sandwich = new Sandwich();
        sandwich.setName("Sandwich non glutenné");
        sandwich.setPrice(56.01);
        sandwich.setDescription("Sandwich sans gluten");
        diet = dietRepo.findByName("Sans gluten")
                        .orElseThrow(() -> new RessourceNotFound("Diet not found"));
        diet.getSandwiches().add(sandwich);
        sandwich.getDiets().add(diet);
        sandwichRepo.save(sandwich);
        dietRepo.save(diet);
        ingredients = new HashMap<>(
                Map.of(
                        ingredientRepo.findByName("Pain sans gluten").get().getId(), 1.0,
                        ingredientRepo.findByName("Tomate").get().getId(), 2.0,
                        ingredientRepo.findByName("Oignon").get().getId(), 1.0,
                        ingredientRepo.findByName("Jambon").get().getId(), 250.0
                )
        );
        sandwichServ.setIngredientsToSandwich(sandwich.getId(), ingredients);


        sandwich = new Sandwich();
        sandwich.setName("Sandwich crudités");
        sandwich.setPrice(0.01);
        sandwich.setDescription("Sandwich de crudités");
        diet = dietRepo.findByName("Vegan")
                .orElseThrow(() -> new RessourceNotFound("Diet not found"));
        diet.getSandwiches().add(sandwich);
        sandwich.getDiets().add(diet);
        sandwichRepo.save(sandwich);
        dietRepo.save(diet);
        ingredients = new HashMap<>(
                Map.of(
                        ingredientRepo.findByName("Pain").get().getId(), 1.0,
                        ingredientRepo.findByName("Tomate").get().getId(), 2.0,
                        ingredientRepo.findByName("Oignon").get().getId(), 1.0
                )
        );
        sandwichServ.setIngredientsToSandwich(sandwich.getId(), ingredients);

        //Add a user
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("JohnDoe@gmail.com");
        user.setPassword("pass");
        userRepo.save(user);

        user = new User();
        user.setFirstName("Jane");
        user.setLastName("Colic");
        user.setEmail("JaneColic@gmail.com");
        user.setPassword("pass");
        userRepo.save(user);

    }
}
