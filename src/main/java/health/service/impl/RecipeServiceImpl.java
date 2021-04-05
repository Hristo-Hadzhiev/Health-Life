package health.service.impl;

import health.model.entity.Recipe;
import health.model.entity.enums.RecipeEnum;
import health.model.service.RecipeServiceModel;
import health.model.view.RecipeViewModel;
import health.repository.RecipeRepository;
import health.service.RecipeService;
import health.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public RecipeServiceImpl(RecipeRepository recipeRepository, ModelMapper modelMapper, UserService userService) {
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void createAndAddRecipe(RecipeServiceModel recipeServiceModel) {
        Recipe recipe = modelMapper.map(recipeServiceModel, Recipe.class);
        recipe.setAuthor(userService.findByUsername(userService.getUsername()));
        recipe.setCreatedDate(LocalDateTime.now());

        recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> findRecipesByType(String starter) {

        return recipeRepository.findAllByTypeOfRecipe(RecipeEnum.valueOf(starter));
    }

    @Override
    public RecipeViewModel findById(String id) {

        return this.recipeRepository.findById(id)
                .map(item -> {
                    return this.modelMapper.map(item, RecipeViewModel.class);

                }).orElse(null);
    }

    @Override
    public List<RecipeViewModel> findAllRecipes() {
        return recipeRepository
                .findAll()
                .stream()
                .map(recipe -> this.modelMapper.map(recipe, RecipeViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean recipeExists(String name) {

        return recipeRepository.findByName(name).isPresent();
    }

    @Override
    public List<String> separateRecipe(String id) {
        String recipeProducts = Objects.requireNonNull(recipeRepository.findById(id).orElse(null)).getProducts();
        return Arrays.asList(recipeProducts.split(" *[;] *"));
    }

    @Override
    public List<String> separateDescription(String id) {
        String recipeProducts = Objects.requireNonNull(recipeRepository.findById(id).orElse(null)).getDescription();
        return Arrays.asList(recipeProducts.split(" *[;] *"));
    }

}
