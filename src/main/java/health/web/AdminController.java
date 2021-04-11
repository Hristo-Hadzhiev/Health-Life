package health.web;

import health.repository.UserRepository;
import health.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private")
public class AdminController {

    private final UserService userService;
    private final AdminService adminService;
    private final RecipeService recipeService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final DietService dietService;
    private final TrainingService trainingService;

    public AdminController(UserService userService, AdminService adminService, RecipeService recipeService, UserRepository userRepository, ModelMapper modelMapper, DietService dietService, TrainingService trainingService) {
        this.userService = userService;
        this.adminService = adminService;
        this.recipeService = recipeService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.dietService = dietService;
        this.trainingService = trainingService;
    }

    @GetMapping("/all-users")
    public String getAllUsers(Model model){

        model.addAttribute("allUsers", userService.findAllUsers());
        return "/admin/users";
    }

    @GetMapping("/delete/user/{id}")
    public String deleteUser(@PathVariable String id){

        adminService.deleteUser(id);

        return "redirect:/private/all-users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/make-admin/{id}")
    public String makeUserAdmin(@PathVariable String id){

        adminService.makeUserAdmin(id);

        return "redirect:/private/all-users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/remove-admin-role/{id}")
    public String removeRoleAdminFromUser(@PathVariable String id){

        adminService.removeAdminRole(id);

        return "redirect:/private/all-users";
    }


    @GetMapping("/all-recipes")
    public String getAllRecipes(Model model){

        model.addAttribute("allRecipes", recipeService.findAllRecipes());

        return "/admin/recipes";
    }

    @GetMapping("/delete/recipe/{id}")
    public String deleteRecipe(@PathVariable String id){

        adminService.deleteRecipe(id);

        return "redirect:/private/all-recipes";
    }

    @GetMapping("/all-diets")
    public String getAllDiets(Model model){

        model.addAttribute("allDiets", dietService.findAllDiets());

        return "/admin/diets";
    }

    @GetMapping("/delete/diet/{id}")
    public String deleteDiet(@PathVariable String id){

        adminService.deleteDiet(id);

        return "redirect:/private/all-diets";
    }

    @GetMapping("/all-trainings")
    public String getAllTrainings(Model model){

        model.addAttribute("allTrainings", trainingService.findAllTrainings());

        return "/admin/trainings";
    }

    @GetMapping("/delete/training/{id}")
    public String deleteTraining(@PathVariable String id){

        adminService.deleteTraining(id);

        return "redirect:/private/all-trainings";
    }

}
