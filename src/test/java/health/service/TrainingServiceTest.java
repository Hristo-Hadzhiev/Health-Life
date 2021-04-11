package health.service;

import health.model.entity.*;
import health.model.entity.enums.RecipeEnum;
import health.model.entity.enums.TargetEnum;
import health.model.entity.enums.UserRole;
import health.model.service.TrainingServiceModel;
import health.model.view.DietViewModel;
import health.model.view.RecipeViewModel;
import health.repository.RecipeRepository;
import health.repository.TrainingRepository;
import health.service.impl.RecipeServiceImpl;
import health.service.impl.TrainingServiceImpl;
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
public class TrainingServiceTest {

    @Mock
    private TrainingService trainingService;

    @Mock
    private TrainingRepository trainingRepository;

    @Mock
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    private Training training;
    private UserEntity userEntity;

    @BeforeEach
    public void setup() throws IOException {

        userEntity = this.getCreatedUsers().get(0);
        training = this.getTraining().get(0);

        trainingService = new TrainingServiceImpl(trainingRepository,
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

    // Create List with Diets
    public List<Diet> getDiets() {
        UserEntity user = getCreatedUsers().get(0);
        Diet first = new Diet();
        Diet second = new Diet();

        first.setName("Сурови топчета с малини").setLikes(10);
        first.setLikes(10);
        first.setId("first");
        first.setAuthor(user);
        first.setTarget(TargetEnum.ОТСЛАБВАНЕ);
        first.setDescription("dwalkdwa;ljdawkldjaw");

        second.setName("Печени топчета с малини");
        second.setLikes(10);
        second.setId("first");
        second.setAuthor(user);
        second.setTarget(TargetEnum.ОТСЛАБВАНЕ);
        second.setDescription("dwalkdwa;ljdawkldjaw");

        return new ArrayList<>(List.of(first, second));
    }

    // Create List with Recipes
    public Set<Recipe> getRecipes() {
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

        return new HashSet<>(List.of(first, second));
    }

    // Create List with Training
    public List<Training> getTraining() {
        UserEntity user = getCreatedUsers().get(0);
        Training first = new Training();
        Training second = new Training();

        first.setName("Сурови топчета с малини");
        first.setId("first");
        first.setAuthor(user);
        first.setTarget(TargetEnum.ОТСЛАБВАНЕ);
        first.setDescription("dwalkdwa;ljdawkldjaw");

        second.setName("Печени топчета с малини");
        second.setId("first");
        second.setAuthor(user);
        second.setTarget(TargetEnum.ОТСЛАБВАНЕ);
        second.setDescription("dwalkdwa;ljdawkldjaw");

        return new ArrayList<>(List.of(first, second));
    }

    //Create Plan
    public Plan getPlan() {

        return new Plan()
                .setDiet(getDiets().get(0))
                .setRecipes(getRecipes())
                .setTraining(getTraining().get(0))
                .setUser(getCreatedUsers().get(0));
    }

    @Test
    void testTrainingExist(){
        Training training = getTraining().get(0);

        Mockito.when(trainingRepository.findByName("Сурови топчета с малини")).thenReturn(Optional.ofNullable(training));
        Assertions.assertTrue(trainingService.trainExist("Сурови топчета с малини"));
    }

    @Test
    void testFindAllTrainings(){
        Training first = getTraining().get(0);
        Training second = getTraining().get(1);

        Mockito.when(trainingRepository.findAll()).thenReturn(List.of(first, second));
        Assertions.assertEquals(2, trainingService.findAllTrainings().size());
    }
    @Test
    void testDeleteById(){
        trainingService.deleteById("id");
        Mockito.verify(trainingRepository).deleteById("id");
    }

    @Test
    void testFindById(){
        trainingService.findById("id");
        Mockito.verify(trainingRepository).findById("id");
    }

}
