package health.service;

import health.model.entity.Recipe;
import health.model.entity.enums.TargetEnum;
import health.model.service.RecipeServiceModel;
import health.model.view.RecipeViewModel;

import java.util.List;

public interface RecipeService {
    void createAndAddRecipe(RecipeServiceModel recipeServiceModel);

    List<Recipe> findRecipesByType(String starter);

    RecipeViewModel findById(String id);

    List<RecipeViewModel> findAllRecipes();

    boolean recipeExists(String username);

    List<String> separateRecipe(String id);

    List<String> separateDescription(String id);

    void deleteById(String id);

    TargetEnum findRecipeTypeByCalories(int cal);

}
