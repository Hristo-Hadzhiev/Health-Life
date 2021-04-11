package health.service.impl;

import health.model.entity.UserEntity;
import health.model.entity.UserRoleEntity;
import health.model.entity.enums.UserRole;
import health.repository.UserRepository;
import health.repository.UserRoleRepository;
import health.service.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {


    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RecipeService recipeService;
    private final DietService dietService;
    private final TrainingService trainingService;
    private final UserService userService;

    public AdminServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository,
                            RecipeService recipeService, DietService dietService,
                            TrainingService trainingService, UserService userService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.recipeService = recipeService;
        this.dietService = dietService;
        this.trainingService = trainingService;
        this.userService = userService;
    }

    @Override
    public void makeUserAdmin(String id) {

        UserEntity user = userRepository.findById(id)
                .orElse(null);
        assert user != null;
        Set<UserRoleEntity> roles = user.getRoles();

        if(roles.size()==1){

            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRole.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRole.USER);

            user.setRoles(Set.of(adminRole,userRole));
            userRepository.save(user);
        }

    }

    @Override
    public void removeAdminRole(String id) {

        UserEntity user = userRepository.findById(id)
                .orElse(null);
        assert user != null;
        Set<UserRoleEntity> roles = user.getRoles();

        if(roles.size()==2){

            UserRoleEntity userRole = userRoleRepository.findByRole(UserRole.USER);

            user.setRoles(Set.of(userRole));
            userRepository.save(user);
        }

    }

    @Override
    public void deleteUser(String id) {
        userService.deleteById(id);
    }

    @Override
    public void deleteRecipe(String id) {
        recipeService.deleteById(id);
    }

    @Override
    public void deleteDiet(String id) {
        dietService.deleteById(id);
    }

    @Override
    public void deleteTraining(String id) {
        trainingService.deleteById(id);
    }

}
