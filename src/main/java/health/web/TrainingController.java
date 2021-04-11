package health.web;

import health.model.binding.TrainingAddBindingModel;
import health.model.service.DietServiceModel;
import health.model.service.TrainingServiceModel;
import health.service.TrainingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/trainings")
public class TrainingController {

    private final ModelMapper modelMapper;
    private final TrainingService trainingService;

    public TrainingController(ModelMapper modelMapper, TrainingService trainingService) {
        this.modelMapper = modelMapper;
        this.trainingService = trainingService;
    }

    @GetMapping("/add")
    public String getAddTraining(Model model){

        if(!model.containsAttribute("train")){
            model.addAttribute("train", new TrainingAddBindingModel());
        }

        if(!model.containsAttribute("trainExistError")){
            model.addAttribute("trainExistError", false);
        }

        return "/trainings/add-training";
    }
    @PostMapping("/add")
    public String addDiet (@Valid @ModelAttribute TrainingAddBindingModel trainingAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("trainingAddBindingModel", trainingAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.trainingAddBindingModel",
                    bindingResult);
            return "redirect:add";
        }
        if(trainingService.trainExist(trainingAddBindingModel.getTrainingName())){
            System.out.println();
            redirectAttributes.addFlashAttribute("trainingAddBindingModel", trainingAddBindingModel);
            redirectAttributes.addFlashAttribute("trainExistError", true);

            return "redirect:add";
        }

        TrainingServiceModel trainingServiceModel = modelMapper.
                map(trainingAddBindingModel, TrainingServiceModel.class);
        trainingService.addTrainingToDB(trainingServiceModel);

        return "redirect:/home";
    }
    @GetMapping("/details/{id}")
    public ModelAndView getTrainingDetails(@PathVariable String id, ModelAndView modelAndView){

        modelAndView.addObject("training", trainingService.findById(id));
        modelAndView.setViewName("/trainings/training-details");

        return modelAndView;
    }

}
