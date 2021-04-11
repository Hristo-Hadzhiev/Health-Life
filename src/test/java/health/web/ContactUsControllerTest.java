package health.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class ContactUsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    public void getContactUs_withAnonymousUser_shouldReturnError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contactUs"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("contactUsBindingModel"))
                .andExpect(view().name("contactUs"));
    }
    @Test
    public void get_contact_us_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contactUs"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("contactUsBindingModel"))
                .andExpect(view().name("contactUs"));
    }
    @Test
    public void post_method_contact_us_should_return_valid_status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/contactUs"));
    }

}
