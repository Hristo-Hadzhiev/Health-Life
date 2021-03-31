package health.model.service;

import health.model.entity.UserEntity;
import health.model.entity.enums.RecipeEnum;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RecipeServiceModel {
    private String name;
    private RecipeEnum typeOfRecipe;
    private String products;
    private int cookingTime;
    private int calories;
    private String description;
    private String image;
    private UserEntity author;
    private LocalDate createdDate;

    public RecipeServiceModel() {
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

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
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

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
