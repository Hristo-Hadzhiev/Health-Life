package health.service.impl;

import health.model.entity.UserEntity;
import health.model.entity.UserRoleEntity;
import health.model.entity.enums.UserRole;
import health.repository.DietRepository;
import health.repository.RecipeRepository;
import health.repository.UserRepository;
import health.repository.UserRoleRepository;
import health.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {


    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RecipeRepository recipeRepository;
    private final DietRepository dietRepository;

    public AdminServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, RecipeRepository recipeRepository, DietRepository dietRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.recipeRepository = recipeRepository;
        this.dietRepository = dietRepository;
    }

    @Override
    public void makeUserAdmin(String id) {

        UserEntity user = userRepository.findById(id)
                .orElse(null);
        assert user != null;
        List<UserRoleEntity> roles = user.getRoles();

        if(roles.size()==1){

            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRole.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRole.USER);

            user.setRoles(List.of(adminRole, userRole));
            userRepository.save(user);
        }

    }

    @Override
    public void removeAdminRole(String id) {

        UserEntity user = userRepository.findById(id)
                .orElse(null);
        assert user != null;
        List<UserRoleEntity> roles = user.getRoles();

        if(roles.size()==2){

            UserRoleEntity userRole = userRoleRepository.findByRole(UserRole.USER);

            user.setRoles(List.of( userRole));
            userRepository.save(user);
        }

    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteRecipe(String id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public void deleteDiet(String id) {
        dietRepository.deleteById(id);
    }

}
