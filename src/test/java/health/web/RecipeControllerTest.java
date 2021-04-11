package health.web;

import health.model.entity.Recipe;
import health.model.entity.UserEntity;
import health.model.entity.UserRoleEntity;
import health.model.entity.enums.RecipeEnum;
import health.model.entity.enums.UserRole;
import health.repository.RecipeRepository;
import health.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTest {


    private static final String USER_TEST = "admin";
    private static final String RECIPE_ID = "recipe";

    private Recipe recipe;
    private String recipeId;
    private UserEntity user;
    private RecipeRepository mockedRecipeRepository;


    @BeforeEach
    public void init() {

        this.mockedRecipeRepository = Mockito.mock(RecipeRepository.class);

        user = new UserEntity()
                .setUsername(USER_TEST)
                .setPassword(("admin"))
                .setEmail("admin@abv.bg")
                .setRoles(Set.of(new UserRoleEntity().setRole(UserRole.USER)));

        recipe = new Recipe("Рулца от патладжан с орехов пълнеж",
                RecipeEnum.ОСНОВНО, "- 3 средно големи патладжана\n",
                40,
                200,
                ".Нарежете патладжаните" ,
                100,
                "/images/recipe/main/Рулца от патладжан с орехов пълнеж.jpg",user);
        recipe.setId(RECIPE_ID);

        mockedRecipeRepository.save(recipe);

        recipeId = recipe.getId();
    }

    @Autowired
    private  MockMvc mockMvc;

    @Test
    public void add_recipe_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipeAddBindingModel"))
                .andExpect(view().name("/recipes/add-recipe"));
    }

    @Test
    public void postMethod_addRecipe_shouldReturnStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/recipes/add"));

    }

    @Test
    public void recipes_starter_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/starter"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipes/starter"));
    }

    @Test
    public void recipes_main_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/main-dish"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipes/main-dish"));
    }
    @Test
    public void recipes_dessert_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/dessert"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipes/dessert"));
    }
    @Test
    public void recipes_vegetarian_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/vegetarian"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipes/vegetarian"));
    }

//    @Test
//    @WithMockUser(username = USER_TEST, authorities = {"USER","ADMIN"})
//    public void recipesDetailsId_shouldReturnValidStatus() throws Exception {
//        System.out.println();
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/recipes/details/{id}" + RECIPE_ID))
//                .andExpect(status().isOk());
//    }
}

