package health.web;

import health.model.binding.ContactUsBindingModel;
import health.model.service.ContactUsServiceModel;
import health.service.ContactUsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ContactUsController {

    private final ContactUsService contactService;
    private final ModelMapper modelMapper;

    public ContactUsController(ContactUsService contactService, ModelMapper modelMapper) {
        this.contactService = contactService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/contactUs")
    public String getContactForm(Model model){

        if(!model.containsAttribute("contactUsBindingModel")){
            model.addAttribute("contactUsBindingModel", new ContactUsBindingModel());
        }

        return "contact";
    }

    @PostMapping("/contactUs")
    public String sendContactUs(@Valid @ModelAttribute ContactUsBindingModel contactUsBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("contactUsBindingModel", contactUsBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactUsBindingModel",
                    bindingResult);

            return "redirect:contactUs";
        }
        ContactUsServiceModel contact = modelMapper.
                map(contactUsBindingModel, ContactUsServiceModel.class);

        contactService.saveMessageInDb(contact);

        return "redirect:/home";
    }
}
