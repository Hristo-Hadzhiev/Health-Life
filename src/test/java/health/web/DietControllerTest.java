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
public class DietControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void add_diet_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/diets/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("dietAddBindingModel"))
                .andExpect(view().name("/diets/add-diet"));
    }
    @Test
    public void post_method_add_diet_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/register"));

    }
    @Test
    public void get_diet_explanation_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/diets/explanation"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("topFive"))
                .andExpect(view().name("/diets/explanation"));
    }
    //TODO / method return error - null pointer
    @Test
    public void diet_details_id_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/details/{id}" ))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("diet"))
                .andExpect(view().name("/diets/diet-details"));
    }
    @Test
    public void show_all_diets_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/diets/show-all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("loss"))
                .andExpect(model().attributeExists("gain"))
                .andExpect(model().attributeExists("maintain"))
                .andExpect(view().name("/diets/show-all"));
    }
}
