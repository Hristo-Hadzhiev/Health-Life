package health.web;

import health.model.entity.UserEntity;
import health.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private UserEntity testUser;
    private UserRepository mockedUserRepository;

    @BeforeEach
    public void init() {
        this.testUser = new UserEntity() {
            {
                setUsername("Pesho");
                setPassword("12345");
                setEmail("Pesho@abv.bg");
                setRoles(getRoles());
            }
        };
        this.mockedUserRepository = Mockito.mock(UserRepository.class);
    }

    @Autowired
    private  MockMvc mockMvc;


    @Test
    public void login_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

    }

    @Test
    public void register_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("userRegisterBindingModel"));
    }

    @Test
    public void login_should_return_valid_param() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                .param("username", "admin@abv.bg")
                .param("password", "topsecret")
        );
    }
    @Test
    public void register_should_return_invalid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register/434343"))
                .andExpect(status().isNotFound());
    }
    @Test
//    @WithMockUser(username = "user", password = "user", authorities = {"ADMIN"})
    public void logout_work_correct() throws Exception {
        mockMvc.perform(logout());
    }


}
