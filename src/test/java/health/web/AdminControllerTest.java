package health.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", roles = {"USER", "ADMIN"})
    public void getAllUsers_withAdminRole_shouldReturnStatus403() throws Exception {
        mockMvc.perform(get("/private/all-users"))
                .andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER", "ADMIN", "ROOT_ADMIN"})
    public void getAllUsers_withRootAdminRole_shouldReturnStatus200() throws Exception {
        mockMvc.perform(get("/private/all-users"))
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void getAllDiets_withAdminRole_shouldReturnStatus200() throws Exception {
        mockMvc.perform(get("/private/all-diets"))
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER"})
    public void getAllDiets_withUserRole_shouldReturnStatus403() throws Exception {
        mockMvc.perform(get("/private/all-diets"))
                .andExpect(status().is(403));
    }
    @Test
    @WithMockUser(username="admin",roles={"USER", "ADMIN"})
    public void getAllRecipes_withAdminRole_shouldReturnStatus200() throws Exception {
        mockMvc.perform(get("/private/all-recipes"))
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser(username="admin")
    public void getAllRecipes_withUserRole_shouldReturnStatus403() throws Exception {
        mockMvc.perform(get("/private/all-recipes"))
                .andExpect(status().is(403));
    }
    @Test
    @WithMockUser(username="admin",roles={"USER", "ADMIN"})
    public void getAllTraining_withAdminRole_shouldReturnStatus200() throws Exception {
        mockMvc.perform(get("/private/all-trainings"))
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser(username="admin")
    public void getAllTraining_withUserRole_shouldReturnStatus403() throws Exception {
        mockMvc.perform(get("/private/all-trainings"))
                .andExpect(status().is(403));
    }
}
