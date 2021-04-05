package health.service;

import health.model.entity.Recipe;
import health.model.entity.UserEntity;
import health.model.entity.UserRoleEntity;
import health.model.entity.enums.RecipeEnum;
import health.model.entity.enums.UserRole;
import health.model.view.RecipeViewModel;
import health.repository.RecipeRepository;
import health.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private RecipeRepository mockRecipeRepository;

    @Mock
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    private RecipeViewModel recipeViewModel;
    private Recipe recipe;
    private UserEntity userEntity;

    @BeforeEach
    public void setup() throws IOException {

        userEntity = this.getUserEntity();
        recipe = this.getRecipe(userEntity);
        recipeViewModel = this.getRecipeViewModel(recipe);

        recipeService = new RecipeServiceImpl(mockRecipeRepository,
                modelMapper, userService);
    }

    @Test
    public void testGetAllRecipes() {

        when(recipeService.findAllRecipes()).thenReturn(List.of(recipeViewModel));
        System.out.println();
        List<RecipeViewModel> recipeViewModels = recipeService.findAllRecipes();
        RecipeViewModel actualDto = recipeViewModels.get(0);

        Assertions.assertEquals(recipeViewModel.getName(), actualDto.getName());
        Assertions.assertEquals(recipeViewModel.getName(), actualDto.getName());

    }

    public UserEntity getUserEntity() {
        UserEntity user = new UserEntity();
        user.setUsername("pesho");
        user.setEmail("pesho@abv.bg");
        user.setPassword("pesho");
        user.setRoles(getUserRoleEntity());
        user.setId("user");

        return user;
    }

    public List<UserRoleEntity> getUserRoleEntity() {
        List<UserRoleEntity> userRoles = new ArrayList<>();

        UserRoleEntity roleUser = new UserRoleEntity();
        roleUser.setRole(UserRole.USER);
        roleUser.setId("roleUser");
        userRoles.add(roleUser);

        UserRoleEntity adminUser = new UserRoleEntity();
        roleUser.setRole(UserRole.ADMIN);
        roleUser.setId("adminUser");
        userRoles.add(adminUser);

        return userRoles;
    }

    public Recipe getRecipe(UserEntity userEntity) {
        Recipe recipe = new Recipe();

        recipe.setName("Сурови топчета с малини");
        recipe.setTypeOfRecipe(RecipeEnum.ДЕСЕРТ);
        recipe.setCreatedDate(LocalDateTime.now());
        recipe.setCalories(100);
        recipe.setCookingTime(100);
        recipe.setLikes(100);
        recipe.setImage("No image");
        recipe.setDescription("This is only for test");
        recipe.setProducts("No available products");
        recipe.setAuthor(userEntity);
        recipe.setId("recipe");

        return recipe;
    }

    public RecipeViewModel getRecipeViewModel(Recipe recipe) {
        return modelMapper
                .map(recipe, RecipeViewModel.class);
    }

}
