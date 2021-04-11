package health.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "plans")
public class Plan extends BaseClass{

    private UserEntity user;
    private Diet diet;
    private Training training;
    private Set<Recipe> recipes;

    public Plan() {
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public Plan setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    @ManyToOne
    public Diet getDiet() {
        return diet;
    }

    public Plan setDiet(Diet diet) {
        this.diet = diet;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public Plan setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
        return this;
    }

    @ManyToOne
    public Training getTraining() {
        return training;
    }

    public Plan setTraining(Training training) {
        this.training = training;
        return this;
    }
}
