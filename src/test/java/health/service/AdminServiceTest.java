package health.service;

import health.model.entity.Recipe;
import health.model.entity.UserEntity;
import health.model.entity.UserRoleEntity;
import health.model.entity.enums.RecipeEnum;
import health.model.entity.enums.TargetEnum;
import health.model.entity.enums.UserRole;
import health.repository.*;
import health.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    private AdminService service;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private DietService dietService;
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private DietRepository dietRepository;
    @Mock
    private TrainingRepository trainingRepository;

    @BeforeEach
    public void setup() throws IOException {
        userRepository = Mockito.mock(UserRepository.class);
        userRoleRepository = Mockito.mock(UserRoleRepository.class);
        recipeService = Mockito.mock(RecipeService.class);
        dietService = Mockito.mock(DietService.class);
        TrainingService trainingService = Mockito.mock(TrainingService.class);
        UserService userService = Mockito.mock(UserService.class);

        ModelMapper modelMapper = new ModelMapper();

        service = new AdminServiceImpl(userRepository, userRoleRepository,
                recipeService, dietService, trainingService, userService);
    }

    @Test
    public void testDeleteUser() {
        service.deleteUser("id");
        Mockito.verify(userRepository).deleteById("id");
    }

    @Test
    public void testDeleteRecipe() {
        service.deleteRecipe("id");
        Mockito.verify(recipeRepository).deleteById("id");
    }

    @Test
    public void testDeleteDiet() {
        service.deleteDiet("id");
        Mockito.verify(dietRepository).deleteById("id");
    }

    @Test
    public void testDeleteTraining() {
        service.deleteTraining("id");
        Mockito.verify(trainingRepository, Mockito.times(1)).deleteById("id");
    }

//    @Test
//    void testMakeUserAdmin() {
//        UserEntity userEntity = getCreatedUsers().get(0);
//
//        adminService.makeUserAdmin(userEntity.getId());
//        Mockito.verify(adminService.makeUserAdmin("id"))
//    }

//    @Test
//    void testRemoveUserAdmin(){

//    }


    //Create List with users
    public List<UserEntity> getCreatedUsers() {

        UserEntity firstUser = new UserEntity();
        firstUser.setId("1234");
        firstUser.setUsername("pesho");
        firstUser.setEmail("pesho@abv.bg");
        firstUser.setPassword("pesho");
        firstUser.setRoles(new HashSet<>(List.of(getUserRoleEntity().get(0))));

        UserEntity secondUser = new UserEntity();
        secondUser.setId("2345");
        secondUser.setUsername("gosho");
        secondUser.setEmail("gosho@abv.bg");
        secondUser.setPassword("gosho");
        secondUser.setRoles(new HashSet<>(getUserRoleEntity()));

        return new ArrayList<>(List.of(firstUser, secondUser));
    }

    // Create Set with userRoles
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
        first.setTarget(TargetEnum.ОТСЛАБВАНЕ);
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

}

