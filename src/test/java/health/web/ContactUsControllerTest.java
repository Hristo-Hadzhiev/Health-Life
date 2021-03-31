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
public class ContactUsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void get_contact_us_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contactUs"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("contactUsBindingModel"))
                .andExpect(view().name("contact"));
    }
    @Test
    public void post_method_contact_us_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/contactUs"));
//                .param("username", "йдклвайдклвйадклвай")
//                .param("email", "Pesho@abv.bg")
//                .param("password", "12345")
//                .param("dwadwa", "1dwadwa2345")

    }
}
