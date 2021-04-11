package health.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user")
    public void addTraining_withUser_shouldReturnStatus200() throws Exception {
        mockMvc.perform(get("/trainings/add"))
                .andExpect(status().is(200));
    }
    @Test
    public void postMethod_addTrainingWithUser_shouldReturnStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/trainings/add"));

    }

//    @Test
//    @WithAnonymousUser
//    public void pageGetPlan_withoutUser_shouldReturnStatus200() throws Exception {
//        mockMvc.perform(get("/plans/getPlan"))
//                .andExpect(status().is(403));
//    }

}