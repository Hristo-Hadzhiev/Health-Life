package health.service;

import health.model.entity.Diet;
import health.model.entity.Recipe;
import health.model.entity.UserEntity;
import health.model.entity.UserRoleEntity;
import health.model.entity.enums.RecipeEnum;
import health.model.entity.enums.TargetEnum;
import health.model.entity.enums.UserRole;
import health.model.service.DietServiceModel;
import health.model.view.DietViewModel;
import health.model.view.RecipeViewModel;
import health.repository.DietRepository;
import health.repository.RecipeRepository;
import health.service.impl.DietServiceImpl;
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
public class DietServiceTest {

    @Mock
    private DietService dietService;

    @Mock
    private DietRepository dietRepository;

    @Mock
    private UserService userService;

    @Mock
    private RecipeService recipeService;

    @Autowired
    private ModelMapper modelMapper;

    private DietViewModel dietViewModel;
    private Diet diet;
    private UserEntity userEntity;

    @BeforeEach
    public void setup() throws IOException {

        userEntity = this.getCreatedUsers().get(0);
        diet = this.getDiets().get(0);
        dietViewModel = this.getDietViewModel(diet);

        dietService = new DietServiceImpl(dietRepository,
                modelMapper, userService, recipeService);
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

    //Create View Model - Recipe
    public DietViewModel getDietViewModel(Diet diet) {
        return modelMapper
                .map(diet, DietViewModel.class);
    }

    @Test
    void testFindAllDiets(){
        Diet first = getDiets().get(0);
        Diet second = getDiets().get(1);

        List<DietViewModel> dietViewModels = new ArrayList<>();
        dietViewModels.add(this.modelMapper.map(first, DietViewModel.class));
        dietViewModels.add(this.modelMapper.map(second, DietViewModel.class));

        Mockito.when(dietRepository.findAll()).thenReturn(List.of(first, second));
        Assertions.assertEquals(2, dietService.findAllDiets().size());
    }

    @Test
    void testFindAllDietsByTarget(){
        Diet first = getDiets().get(0);
        Diet second = getDiets().get(1);

        List<DietViewModel> dietViewModels = new ArrayList<>();
        dietViewModels.add(this.modelMapper.map(first, DietViewModel.class));
        dietViewModels.add(this.modelMapper.map(second, DietViewModel.class));

        Mockito.when(dietRepository.findAll()).thenReturn(List.of(first, second));
        Assertions.assertEquals(2, dietService.findAllDiets().size());
    }

    @Test
    void testDietExist(){
        Diet first = getDiets().get(0);

        Mockito.when(dietRepository.findByName("Сурови топчета с малини")).thenReturn(Optional.ofNullable(first));
        Assertions.assertTrue(dietService.dietExists("Сурови топчета с малини"));
    }

    @Test
    void testDeleteById(){
        dietService.deleteById("id");
        Mockito.verify(dietRepository).deleteById("id");
    }


//    void addDietToDB(DietServiceModel dietModel);

//    List<Diet> getTopFiveDiet();

}
