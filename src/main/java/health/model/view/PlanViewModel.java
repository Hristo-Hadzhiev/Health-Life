package health.model.view;

import health.model.entity.Diet;
import health.model.entity.Recipe;
import health.model.entity.Training;
import health.model.entity.UserEntity;

import java.util.Set;

public class PlanViewModel {

    private UserEntity user;
    private Diet diet;
    private Training training;
    private Set<Recipe> recipes;

    public PlanViewModel() {
    }

    public UserEntity getUser() {
        return user;
    }

    public PlanViewModel setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public Diet getDiet() {
        return diet;
    }

    public PlanViewModel setDiet(Diet diet) {
        this.diet = diet;
        return this;
    }

    public Training getTraining() {
        return training;
    }

    public PlanViewModel setTraining(Training training) {
        this.training = training;
        return this;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public PlanViewModel setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
        return this;
    }
}
