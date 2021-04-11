package health.web;

import health.model.binding.UserRegisterBindingModel;
import health.model.service.UserServiceModel;
import health.service.PlanService;
import health.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final PlanService planService;

    public UserController(ModelMapper modelMapper, UserService userService, PlanService planService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.planService = planService;
    }

    @GetMapping("/login")
    public String getLogin(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            if(!model.containsAttribute("error")){
                model.addAttribute("error", false);
            }
            return "login";
        }
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String getRegister(Model model){

        if(!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("incorrectPassword", false);
            model.addAttribute("userExistError", false);
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

        if(bindingResult.hasErrors() || !userRegisterBindingModel
                .getPassword()
                .equals(userRegisterBindingModel.getRepeatPassword())){

            if (!userRegisterBindingModel.getPassword()
                    .equals(userRegisterBindingModel.getRepeatPassword())){
                redirectAttributes.addAttribute("incorrectPassword", true);
            }

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
    @PostMapping("/login-error")
    public ModelAndView errorLogin(@ModelAttribute("username") String username,
                                   RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        redirectAttributes.addFlashAttribute("error", true);
        redirectAttributes.addFlashAttribute("username", username);

        modelAndView.setViewName("redirect:/users/login");
        return modelAndView;

    }

    @GetMapping("/profile")
    public String getProfile(Model model){

        model.addAttribute("planUser",planService.findUserPlan());

        return "profile";
    }
}
