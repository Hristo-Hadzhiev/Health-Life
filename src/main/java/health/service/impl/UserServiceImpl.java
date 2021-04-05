package health.service.impl;

import health.model.entity.UserEntity;
import health.model.entity.UserRoleEntity;
import health.model.entity.enums.UserRole;
import health.model.service.UserServiceModel;
import health.model.view.UserViewModel;
import health.repository.UserRepository;
import health.repository.UserRoleRepository;
import health.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers() {

        if (userRepository.count() == 0) {
            UserRoleEntity rootAdminRole = new UserRoleEntity().setRole(UserRole.ROOT_ADMIN);
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);

            userRoleRepository.saveAll(List.of(rootAdminRole,adminRole, userRole));

            UserEntity rootadmin = new UserEntity()
                    .setUsername("rootadmin")
                    .setPassword(passwordEncoder.encode("rootadmin"))
                    .setEmail("rootadmin@abv.bg")

                    .setRoles(List.of(rootAdminRole, adminRole, userRole));

            UserEntity admin = new UserEntity()
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("admin"))
                    .setEmail("admin@abv.bg")

                    .setRoles(List.of(adminRole, userRole));

            UserEntity user1 = new UserEntity()
                    .setUsername("user")
                    .setPassword(passwordEncoder.encode("user"))
                    .setEmail("user@abv.bg")
                    .setRoles(List.of(userRole));

            UserEntity user2 = new UserEntity()
                    .setUsername("ivan")
                    .setPassword(passwordEncoder.encode("ivan"))
                    .setEmail("ivan@abv.bg")
                    .setRoles(List.of(userRole));

            UserEntity user3 = new UserEntity()
                    .setUsername("ana")
                    .setPassword(passwordEncoder.encode("ana"))
                    .setEmail("ana@abv.bg")
                    .setRoles(List.of(userRole));

            UserEntity user4 = new UserEntity()
                    .setUsername("radost")
                    .setPassword(passwordEncoder.encode("radost"))
                    .setEmail("radost@abv.bg")
                    .setRoles(List.of(userRole));


            userRepository.saveAndFlush(rootadmin);
            userRepository.saveAndFlush(admin);
            userRepository.saveAndFlush(user1);
            userRepository.saveAndFlush(user2);
            userRepository.saveAndFlush(user3);
            userRepository.saveAndFlush(user4);

        }
    }
    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean userNameExists(String username) {

        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
            return userPrincipal.getUsername();
        }
        return null;
    }

    @Override
    public List<UserViewModel> findAllUsers() {

        return userRepository
                .findAll()
                .stream()
                .map(userEntity -> this.modelMapper.map(userEntity, UserViewModel.class))
                .collect(Collectors.toList());
    }


    @Override
    public UserEntity findById(String id) {

        return userRepository.findById(id)
                .orElse(null);
    }

    @Scheduled(cron = "0 0 8 * * *" )
    public void showCountOfRegisterUsers(){

        System.out.println("Count of register users: " + userRepository.count());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.
                findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " was not found."));

        return mapToUserDetails(userEntity);
    }

    private UserDetails mapToUserDetails(UserEntity userEntity) {
        List<GrantedAuthority> authorities =
                userEntity.
                        getRoles().
                        stream().
                        map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).
                        collect(Collectors.toList());

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }
}
