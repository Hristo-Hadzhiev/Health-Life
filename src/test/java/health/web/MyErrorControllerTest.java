package health.web;

import health.model.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MyErrorControllerTest {

    private UserEntity user;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void error_rout_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/doNotExist"))
                .andExpect(status().is(404));
    }


}