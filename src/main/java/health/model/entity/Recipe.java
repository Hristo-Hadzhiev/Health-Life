package health.model.entity;

import health.model.entity.enums.RecipeEnum;
import health.model.entity.enums.TargetEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe extends BaseClass{

    private String name;
    private RecipeEnum typeOfRecipe;
    private String products;
    private int cookingTime;
    private int calories;
    private String description;
    private int likes;
    private String image;
    private UserEntity author;
    private TargetEnum target;
    private LocalDateTime createdDate = LocalDateTime.now();


    public Recipe() {
    }

    public Recipe(String name, RecipeEnum typeOfRecipe,
                  String products, int cookingTime, int calories, String description, int likes, String image, UserEntity author) {
        this.name = name;
        this.typeOfRecipe = typeOfRecipe;
        this.products = products;
        this.cookingTime = cookingTime;
        this.calories = calories;
        this.description = description;
        this.likes = likes;
        this.image = image;
        this.author = author;
    }

    @Column(nullable = false, unique = true)
    @Length(min = 2, message = "Name must be at least 2 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type_of_recipe", nullable = false)
    @Enumerated(EnumType.STRING)
    public RecipeEnum getTypeOfRecipe() {
        return typeOfRecipe;
    }

    public void setTypeOfRecipe(RecipeEnum typeOfRecipe) {
        this.typeOfRecipe = typeOfRecipe;
    }

    @Column(columnDefinition = "TEXT")
    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    @Column(name = "cooking_time")
    @Min(value = 1, message = "Time cannot be less then 1 minutes")
    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Column(name = "calories", nullable = false)
    @Min(value = 1, message = "Calories cannot be less then 1")
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }


    @Column(nullable = false, columnDefinition = "TEXT")
    @Length(min = 10, message = "Description must be at least 10 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "likes", nullable = false)
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Column(name = "image", nullable = false, columnDefinition = "TEXT")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Enumerated(EnumType.STRING)
    public TargetEnum getTarget() {
        return target;
    }

    public Recipe setTarget(TargetEnum target) {
        this.target = target;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }


    @PastOrPresent(message = "Date cannot be at the future")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
