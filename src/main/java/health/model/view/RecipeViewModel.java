package health.model.view;

import health.model.entity.UserEntity;
import health.model.entity.enums.RecipeEnum;

import java.time.LocalDateTime;
import java.util.List;

public class RecipeViewModel {

    private String id;
    private String name;
    private RecipeEnum typeOfRecipe;
    private int cookingTime;
    private int calories;
    private String description;
    private String image;
    private int likes;
    private LocalDateTime createdDate;
    private UserEntity author;

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public RecipeViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RecipeEnum getTypeOfRecipe() {
        return typeOfRecipe;
    }

    public void setTypeOfRecipe(RecipeEnum typeOfRecipe) {
        this.typeOfRecipe = typeOfRecipe;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }
}
