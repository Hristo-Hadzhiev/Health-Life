package health.service.impl;

import health.model.entity.ContactUs;
import health.model.service.ContactUsServiceModel;
import health.repository.ContactUsRepository;
import health.service.ContactUsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    private final ModelMapper modelMapper;
    private final ContactUsRepository contactUsRepository;

    public ContactUsServiceImpl(ModelMapper modelMapper, ContactUsRepository contactUsRepository) {
        this.modelMapper = modelMapper;
        this.contactUsRepository = contactUsRepository;
    }

    @Override
    public void saveMessageInDb(ContactUsServiceModel contact) {
        ContactUs contactUs = modelMapper.
                map(contact, ContactUs.class);
        contactUs.setDateMessage(LocalDateTime.now());
        contactUsRepository.save(contactUs);
    }
}
