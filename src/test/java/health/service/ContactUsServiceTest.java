package health.service;

import health.model.service.ContactUsServiceModel;
import health.repository.ContactUsRepository;
import health.service.impl.ContactUsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ContactUsServiceTest {


    private ContactUsService service;
    private ContactUsRepository contactUsRepository;

    @BeforeEach
    public void setup() throws IOException {
        ModelMapper modelMapper = new ModelMapper();
        contactUsRepository = Mockito.mock(ContactUsRepository.class);

        service = new ContactUsServiceImpl(modelMapper, contactUsRepository);
    }

//    @Test
//    void testSaveMessageInDb(ContactUsServiceModel contact) {
//    }

}