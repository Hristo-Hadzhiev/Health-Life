package health.service;

import health.model.entity.UserEntity;
import health.model.service.UserServiceModel;
import health.model.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface UserService extends UserDetailsService {

    void seedUsers();

    void registerUser(UserServiceModel userServiceModel);

    boolean userNameExists(String username);

    UserEntity findByUsername(String username);

    String getUsername();

    List<UserViewModel> findAllUsers();

    UserEntity findById(String id);



}
