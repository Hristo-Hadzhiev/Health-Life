package health.web;

import health.model.entity.UserEntity;
import health.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private UserEntity testUser;

    @Mock
    private UserRepository mockedUserRepository;

    @BeforeEach
    public void setUp() {
        this.mockedUserRepository = Mockito.mock(UserRepository.class);
    }

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetUserLoginPage() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

    }

//    @WithMockUser(username = "ivan")
//    @Test
//    public void testProfilePage() throws Exception {
//        mockMvc.perform(get("/users/profile"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("profile"));
//
//    }

    @Test
    public void testGetUserRegisterPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("userRegisterBindingModel"));
    }

    @Test
    public void testPostUserRegister() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/users/register")
                .param("username", "TestName")
                .param("email", "test@mail.bg")
                .param("password", "123123Test@")
                .param("repeatPassword", "123123Test@123")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(mvcResult -> {
                    "/users/login".equals(mvcResult.getModelAndView().getViewName());
                });
    }


    @Test
    public void testPostUserRegisterPageWithWrongPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/users/register")
                .param("username", "TestName")
                .param("email", "test@mail.bg")
                .param("password", "123123Test@")
                .param("repeatPassword", "123123Test@2222")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(mvcResult -> {
                    System.out.println();
                    "redirect:register".equals(mvcResult.getModelAndView().getViewName());
                });
    }

    @Test
    @WithMockUser(username = "user", authorities = {"ROOT_ADMIN"})
    public void postAdminPageWithRootAdminRoles() throws Exception {
        mockMvc.perform(get("/private/users-all")
                .with(csrf()))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void wrongRegisterPageShouldNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register/434343"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void logoutCorrect() throws Exception {
        mockMvc.perform(logout());
    }

    public void init() {
        this.testUser = new UserEntity() {
            {
                setUsername("Pesho");
                setPassword("12345");
                setEmail("Pesho@abv.bg");
                setRoles(getRoles());
            }
        };
    }
}
