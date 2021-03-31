package health.service.impl;

import health.model.entity.UserEntity;
import health.model.entity.UserRoleEntity;
import health.model.entity.enums.UserRole;
import health.repository.UserRepository;
import health.service.AdminService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceImplTest {

    private AdminService adminService;
    private UserEntity user;
    private UserRoleEntity userRoleEntity;
    private UserRepository userRepository;

    @BeforeEach
    public void initUser(){

        user = new UserEntity();
        user.setId("user");

        userRoleEntity = new UserRoleEntity();
        userRoleEntity.setId("role");

        userRoleEntity.setRole(UserRole.USER);
        user.setUsername("pesho")
                .setPassword("pesho")
                .setEmail("pesho@abv.bg")
                .setRoles(List.of(userRoleEntity));
        userRepository.save(user);
    }

    @Test
    void makeUserAdmin() {
        adminService.makeUserAdmin(user.id);
        Assertions.assertEquals(user.getRoles().size(),2 );
    }

    @Test
    void removeAdminRole() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void deleteRecipe() {
    }

    @Test
    void deleteDiet() {
    }
}