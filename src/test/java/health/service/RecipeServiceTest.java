package health.service;

import health.model.entity.Recipe;
import health.model.entity.UserEntity;
import health.model.entity.UserRoleEntity;
import health.model.entity.enums.RecipeEnum;
import health.model.entity.enums.TargetEnum;
import health.model.entity.enums.UserRole;
import health.model.view.RecipeViewModel;
import health.repository.RecipeRepository;
import health.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    private RecipeViewModel recipeViewModel;
    private Recipe recipe;
    private UserEntity userEntity;

    @BeforeEach
    public void setup() throws IOException {

        userEntity = this.getCreatedUsers().get(0);
        recipe = this.getRecipes().get(0);
        recipeViewModel = this.getRecipeViewModel(recipe);

        recipeService = new RecipeServiceImpl(recipeRepository,
                modelMapper, userService);
    }

    //Create List with users
    public List<UserEntity> getCreatedUsers() {

        UserEntity firstUser = new UserEntity();
        firstUser.setId("1234");
        firstUser.setUsername("pesho");
        firstUser.setEmail("pesho@abv.bg");
        firstUser.setPassword("pesho");
        firstUser.setRoles(getUserRoleEntity());

        UserEntity secondUser = new UserEntity();
        secondUser.setId("2345");
        secondUser.setUsername("gosho");
        secondUser.setEmail("gosho@abv.bg");
        secondUser.setPassword("gosho");
        secondUser.setRoles(getUserRoleEntity());

        return new ArrayList<>(List.of(firstUser, secondUser));
    }

    // Create Set with userRoles
    public Set<UserRoleEntity> getUserRoleEntity() {
        Set<UserRoleEntity> userRoles = new HashSet<>();

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

    // Create List with Recipes
    public List<Recipe> getRecipes() {
        UserEntity user = getCreatedUsers().get(0);
        Recipe first = new Recipe();
        Recipe second = new Recipe();

        first.setName("Сурови топчета с малини");
        first.setTypeOfRecipe(RecipeEnum.ДЕСЕРТ);
        first.setCreatedDate(LocalDateTime.now());
        first.setCalories(100);
        first.setCookingTime(100);
        first.setLikes(100);
        first.setImage("No image");
        first.setDescription("This is only for test");
        first.setProducts("prod1; prod2");
        second.setTarget(TargetEnum.ОТСЛАБВАНЕ);
        first.setAuthor(user);
        first.setId("first");

        second.setName("Печени топчета с малини");
        second.setTypeOfRecipe(RecipeEnum.ОСНОВНО);
        second.setCreatedDate(LocalDateTime.now());
        second.setCalories(222);
        second.setCookingTime(222);
        second.setLikes(102220);
        second.setImage("No image");
        second.setDescription("Step1; Step2");
        second.setProducts("No available products");
        second.setTarget(TargetEnum.НАПЪЛНЯВАНЕ);
        second.setAuthor(user);
        second.setId("second");

        return new ArrayList<>(List.of(first, second));
    }

    //Create View Model - Recipe
    public RecipeViewModel getRecipeViewModel(Recipe recipe) {
        return modelMapper
                .map(recipe, RecipeViewModel.class);
    }

    //TODO Check later
//    @Test
//    void testFindRecipesByType(){
//
//        Recipe recipe = getRecipes().get(0);
//
//        Mockito.when(recipeRepository.findAllByTypeOfRecipe(RecipeEnum.valueOf("ДЕССЕРТ"))).thenReturn(List.of(recipe));
//        Assertions.assertEquals(1, recipeService.findRecipesByType("ДЕССЕРТ").size());
//    }

    @Test
    void testFindById(){
        Recipe recipe = getRecipes().get(0);

        System.out.println();

        Mockito.when(recipeRepository.findById("first")).thenReturn(Optional.of(recipe));
        Assertions.assertEquals(recipeService
                .findById("first")
                .getName(), modelMapper
                .map(recipe, RecipeViewModel.class)
                .getName());
    }

   @Test
   void testFindAllRecipes(){
       Recipe first = getRecipes().get(0);
       Recipe second = getRecipes().get(1);

       List<RecipeViewModel> recipeViewModels = new ArrayList<>();
       recipeViewModels.add(this.modelMapper.map(first, RecipeViewModel.class));
       recipeViewModels.add(this.modelMapper.map(second, RecipeViewModel.class));

       Mockito.when(recipeRepository.findAll()).thenReturn(List.of(first, second));
       Assertions.assertEquals(2, recipeService.findAllRecipes().size());
   }
    @Test
    void testRecipeExist(){
        Recipe first = getRecipes().get(0);

        Mockito.when(recipeRepository.findByName("Сурови топчета с малини")).thenReturn(Optional.ofNullable(first));
        Assertions.assertTrue(recipeService.recipeExists("Сурови топчета с малини"));
    }


    @Test
    void deleteById(){
        recipeService.deleteById("id");
        Mockito.verify(recipeRepository).deleteById("id");
    }

//    @Test
//    void testSeparateRecipe(){
//        Recipe first = getRecipes().get(0);
//
//        Mockito.when(recipeRepository.).thenReturn(Optional.ofNullable(first));
//        Assertions.assertEquals(recipeService.separateRecipe("id"), List.of("prod1", "prod2"));
//
//    }

//    @Test
//    void testSeparateDescription(){
//        Recipe first = getRecipes().get(0);
//
////        Mockito.when(recipeRepository.).thenReturn(Optional.ofNullable(first));
//        Assertions.assertEquals(recipeService.separateDescription("id"), List.of("Step1", "Step2"));
//
//    }



//    TargetEnum findRecipeTypeByCalories(int cal);
}
