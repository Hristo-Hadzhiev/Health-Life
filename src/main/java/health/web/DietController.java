package health.web;

import health.model.binding.DietAddBindingModel;
import health.model.entity.enums.TargetEnum;
import health.model.service.DietServiceModel;
import health.service.DietService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/diets")
public class DietController {

    private final ModelMapper modelMapper;
    private final DietService dietService;

    public DietController(ModelMapper modelMapper, DietService dietService) {
        this.modelMapper = modelMapper;
        this.dietService = dietService;
    }

    @GetMapping("/add")
    public String getAddDiet(Model model){

        if(!model.containsAttribute("dietAddBindingModel")){
            model.addAttribute("dietAddBindingModel", new DietAddBindingModel());
        }
        if(!model.containsAttribute("dietExistError")){
            model.addAttribute("dietExistError", false);
        }
        return "/diets/add-diet";
    }
    @PostMapping("/add")
    public String addDiet (@Valid @ModelAttribute DietAddBindingModel dietAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("dietAddBindingModel", dietAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.dietAddBindingModel",
                    bindingResult);
            return "redirect:add";
        }
        if(dietService.dietExists(dietAddBindingModel.getName())){
            System.out.println();
            redirectAttributes.addFlashAttribute("dietAddBindingModel", dietAddBindingModel);
            redirectAttributes.addFlashAttribute("dietExistError", true);

            return "redirect:add";
        }

        DietServiceModel dietModel = modelMapper.
                map(dietAddBindingModel, DietServiceModel.class);
        dietService.addDietToDB(dietModel);

        return "redirect:/home";
    }

    @GetMapping("/explanation")
    public String getAddExplanation(Model model){

        if(!model.containsAttribute("topFive")){
            model.addAttribute("topFive", dietService.getTopFiveDiet());
        }

        return "/diets/explanation";
    }

    @GetMapping("/details/{id}")
    public ModelAndView getDetails(@PathVariable String id, ModelAndView modelAndView){

        modelAndView.addObject("diet", this.dietService.findById(id));
        modelAndView.setViewName("/diets/diet-details");

        return modelAndView;
    }

    @GetMapping("/show-all")
    public String showAllDiets(Model model){

        model.addAttribute("loss", dietService.findAllDietsByTarget(TargetEnum.ОТСЛАБВАНЕ));
        model.addAttribute("gain", dietService.findAllDietsByTarget(TargetEnum.НАПЪЛНЯВАНЕ));
        model.addAttribute("maintain", dietService.findAllDietsByTarget(TargetEnum.ТОНИЗИРАНЕ));

        return "/diets/show-all";
    }
}
