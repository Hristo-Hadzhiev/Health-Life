package health.web;

import health.model.binding.RecipeAddBindingModel;
import health.model.service.RecipeServiceModel;
import health.service.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    public final ModelMapper modelMapper;
    public final RecipeService recipeService;

    public RecipeController(ModelMapper modelMapper, RecipeService recipeService) {
        this.modelMapper = modelMapper;
        this.recipeService = recipeService;
    }

    @GetMapping("/add")
    public String getAdd(Model model) {

        if (!model.containsAttribute("recipeAddBindingModel")) {
            model.addAttribute("recipeAddBindingModel", new RecipeAddBindingModel());
        }

        return "/recipes/add-recipe";
    }

    @PostMapping("/add")
    public String addRecipe(@Valid @ModelAttribute RecipeAddBindingModel recipeAddBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeAddBindingModel", recipeAddBindingModel);
            return "redirect:add";
        }

        if(recipeService.recipeExists(recipeAddBindingModel.getName())){
            redirectAttributes.addFlashAttribute("recipeAddBindingModel", recipeAddBindingModel);
            redirectAttributes.addFlashAttribute("recipeExistError", true);

            return "redirect:add";
        }

        RecipeServiceModel recipeServiceModel = modelMapper
                .map(recipeAddBindingModel, RecipeServiceModel.class);
        recipeServiceModel.setProducts(recipeAddBindingModel.getProducts().toString());
        recipeService.createAndAddRecipe(recipeServiceModel);

        return "redirect:/";
    }

    @GetMapping("/starter")
    public String getStarter(Model model) {
        model.addAttribute("recipes", recipeService.findRecipesByType("ПРЕДЯСТИЕ"));
        return "/recipes/starter";
    }

    @GetMapping("/main-dish")
    public String getMainDish(Model model) {
        model.addAttribute("recipes", recipeService.findRecipesByType("ОСНОВНО"));
        return "/recipes/main-dish";
    }

    @GetMapping("/dessert")
    public String getDessert(Model model) {
        model.addAttribute("recipes", recipeService.findRecipesByType("ДЕСЕРТ"));
        return "/recipes/dessert";
    }

    @GetMapping("/vegetarian")
    public String getVegetarian(Model model) {
        model.addAttribute("recipes", recipeService.findRecipesByType("ВЕГЕТАРИАНСКО"));
        return "/recipes/vegetarian";
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable String id, ModelAndView modelAndView){

        modelAndView.addObject("item", this.recipeService.findById(id));
        modelAndView.addObject("recipe", recipeService.separateRecipe(id));
        modelAndView.setViewName("/recipes/recipe-details");

        return modelAndView;
    }

}
