package health.web;

import health.model.binding.PlanGetBindingModel;
import health.repository.PlanRepository;
import health.service.PlanService;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Component
@RequestMapping("/plans")
public class PlanController {

    private final PlanService planService;
    private final PlanRepository planRepository;

    public PlanController(PlanService planService, PlanRepository planRepository) {
        this.planService = planService;
        this.planRepository = planRepository;
    }

    @GetMapping("/getPlan")
    public String getPlan(Model model) {

        if (!model.containsAttribute("plan")) {
            model.addAttribute("plan", new PlanGetBindingModel());
        }
        model.addAttribute("isHavePlan", planService.isUserContainPlan());

        return "/plans/get-plan";
    }

    @PostMapping("/getPlan")
    public String getPlan(@Valid @ModelAttribute PlanGetBindingModel planGetBindingModel) {

        planService.createPlanForUser(planGetBindingModel.getTarget());

        return "redirect:/users/profile";
    }

}
