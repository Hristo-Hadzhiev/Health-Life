package health.service;

import health.model.service.ContactUsServiceModel;

public interface ContactUsService {
    void saveMessageInDb(ContactUsServiceModel contact);
}
