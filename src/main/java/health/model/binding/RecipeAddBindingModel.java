package health.model.binding;

import health.model.entity.enums.RecipeEnum;
import org.hibernate.validator.constraints.Length;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

public class RecipeAddBindingModel {

    private String name;
    private RecipeEnum typeOfRecipe;
    private String products;
    private int cookingTime;
    private int calories;
    private String description;
    private String image;
    private LocalDate createdDate;

    public RecipeAddBindingModel() {
    }

    @Length(min = 2, message = "Name must be at least 2 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    public RecipeEnum getTypeOfRecipe() {
        return typeOfRecipe;
    }

    public void setTypeOfRecipe(RecipeEnum typeOfRecipe) {
        this.typeOfRecipe = typeOfRecipe;
    }

    @Length(min = 2, message = "Products length must be at least 2 characters.")
    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    @Min(value = 1, message = "Time cannot be less then 1 minutes")
    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Min(value = 1, message = "Calories cannot be less then 1")
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Length(min = 10, message = "Description must be at least 10 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Image cannot be null!")
    @Length(min = 1)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @PastOrPresent(message = "Date cannot be at the future")
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
