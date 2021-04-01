package health.web;

import health.model.binding.UserRegisterBindingModel;
import health.model.service.UserServiceModel;
import health.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String getRegister(Model model){

        if(!model.containsAttribute("user")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "register";
        }
        return "redirect:/home";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors() ||
                !userRegisterBindingModel
                        .getPassword()
                        .equals(userRegisterBindingModel.getRepeatPassword())){

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult);
            return "redirect:register";
        }

        if(userService.userNameExists(userRegisterBindingModel.getUsername())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userExistError", true);

            return "redirect:register";
        }

        UserServiceModel userServiceModel = modelMapper.
                map(userRegisterBindingModel, UserServiceModel.class);

        userService.registerUser(userServiceModel);

        return "redirect:login";
    }


}
