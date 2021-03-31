package health.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void get_all_users_us_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/private/all-users"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("allUsers"))
                .andExpect(view().name("/admin/users"));
    }
    @Test
    public void get_all_recipes_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/private/all-recipes"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("allRecipes"))
                .andExpect(view().name("/admin/recipes"));
    }
}
