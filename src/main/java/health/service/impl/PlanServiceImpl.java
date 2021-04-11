package health.service.impl;

import health.model.entity.*;
import health.model.entity.enums.TargetEnum;
import health.model.view.PlanViewModel;
import health.repository.*;
import health.service.PlanService;
import health.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlanServiceImpl implements PlanService {

    private final DietRepository dietRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final TrainingRepository trainingRepository;
    private final PlanRepository planRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public PlanServiceImpl(DietRepository dietRepository, UserRepository userRepository, RecipeRepository recipeRepository, TrainingRepository trainingRepository, PlanRepository planRepository, UserService userService, ModelMapper modelMapper) {
        this.dietRepository = dietRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.trainingRepository = trainingRepository;
        this.planRepository = planRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createPlanForUser(String target) {

        Random rand = new Random();
        TargetEnum targetEnum = TargetEnum.valueOf(target);

        Diet diet = dietRepository
                .findAllByTarget(targetEnum)
                .get(rand.nextInt(dietRepository
                        .findAllByTarget(targetEnum)
                        .size()));
        UserEntity user = userRepository.findByUsername(userService.getUsername());

        Set<Recipe> recipes = new HashSet<>(recipeRepository
                .findAllByTarget(targetEnum));

        Training training = trainingRepository
                .findAllByTarget(targetEnum)
                .get(rand.nextInt(trainingRepository
                .findAllByTarget(targetEnum)
                .size()));

        Plan plan;

        if(planRepository.findByUser(user).isEmpty()){
            plan =  new Plan()
                    .setUser(user)
                    .setDiet(diet)
                    .setRecipes(recipes)
                    .setTraining(training);

            planRepository.save(plan);
        }
    }

    @Override
    public Plan findUserPlan() {
        return planRepository
                .findByUser(userService
                        .findByUsername(userService.getUsername())).orElse(null);
    }

    @Override
    public boolean isUserContainPlan() {

        return planRepository
                .findByUser(userService
                        .findByUsername(userService.getUsername())).isPresent();
    }
}
