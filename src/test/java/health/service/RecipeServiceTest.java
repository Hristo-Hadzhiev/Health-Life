package health.service;

import health.model.entity.Recipe;
import health.model.entity.UserEntity;
import health.model.entity.UserRoleEntity;
import health.model.entity.enums.RecipeEnum;
import health.model.entity.enums.UserRole;
import health.model.view.RecipeViewModel;
import health.repository.RecipeRepository;
import health.repository.UserRoleRepository;
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
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

   @Mock
    private RecipeService recipeService;

    @Mock
    private RecipeRepository mockRecipeRepository;

    @Mock
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    private RecipeViewModel recipeViewModel;
    private UserEntity userEntity;
    private UserRoleEntity userRoleEntity;

    @BeforeEach
    public void setup() throws IOException {

        userEntity = new UserEntity();
        userEntity = new UserEntity();
        userEntity.setUsername("pesho");
        userEntity.setEmail("pesho@abv.bg");
        userEntity.setPassword("pesho");
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(UserRole.USER);
        userEntity.setRoles(List.of(userRoleEntity));

        RecipeViewModel recipeViewModel = new RecipeViewModel();

        recipeViewModel.setName("Сурови топчета с малини");
        recipeViewModel.setTypeOfRecipe(RecipeEnum.ДЕСЕРТ);
        recipeViewModel.setCreatedDate(LocalDateTime.now());
        recipeViewModel.setCalories(100);
        recipeViewModel.setCookingTime(100);
        recipeViewModel.setLikes(100);
        recipeViewModel.setImage("No image");
        recipeViewModel.setDescription("This is only for test");
        recipeViewModel.setProducts("No available products");
        recipeViewModel.setAuthor(userEntity);

        recipeService = new RecipeServiceImpl(mockRecipeRepository,
                modelMapper, userService);
    }

    @Test
    public void testProductServiceGetAllProducts() {
        System.out.println();
        when(recipeService.findAllRecipes()).thenReturn(List.of(recipeViewModel));

        List<RecipeViewModel> recipeViewModels = recipeService.findAllRecipes();
        RecipeViewModel actualDto = recipeViewModels.get(0);

        Assertions.assertEquals(recipeViewModel.getName(), actualDto.getName());
        Assertions.assertEquals(recipeViewModel.getName(), actualDto.getName());

    }

}
