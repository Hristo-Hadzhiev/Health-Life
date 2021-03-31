package health.service.impl;

import health.model.entity.Diet;
import health.model.entity.Recipe;
import health.model.entity.UserEntity;
import health.model.entity.enums.DietTargetEnum;
import health.model.service.DietServiceModel;
import health.model.view.DietViewModel;
import health.model.view.RecipeViewModel;
import health.repository.DietRepository;
import health.service.DietService;
import health.service.RecipeService;
import health.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DietServiceImpl implements DietService {

    private final DietRepository dietRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final RecipeService recipeService;

    public DietServiceImpl(DietRepository dietRepository, ModelMapper modelMapper, UserService userService, RecipeService recipeService) {
        this.dietRepository = dietRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @Override
    public void addDietToDB(DietServiceModel dietModel) {

        Diet diet = modelMapper.map(dietModel, Diet.class);
        diet.setAuthor(userService.findByUsername(userService.getUsername()));

        dietRepository.save(diet);
    }


    @Override
    public List<Diet> getTopFiveDiet() {
        return dietRepository.findAll().stream()
                .sorted(Comparator.comparing(Diet::getLikes))
                .limit(5)
                .collect(Collectors.toList());

    }

    @Override
    public DietViewModel findById(String id) {

        return this.dietRepository.findById(id)
                .map(item -> this.modelMapper.map(item, DietViewModel.class)).orElse(null);
    }

    @Override
    public List<DietViewModel> findAllDietsByTarget(DietTargetEnum losingWeight) {

        return dietRepository.findAllByTarget(losingWeight)
                .stream()
                .map(diet -> this.modelMapper.map(diet, DietViewModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<DietViewModel> findAllDiets() {
        return dietRepository
                .findAll()
                .stream()
                .map(diet -> this.modelMapper.map(diet, DietViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean dietExists(String name) {

        return dietRepository.findByName(name).isPresent();
    }


}
