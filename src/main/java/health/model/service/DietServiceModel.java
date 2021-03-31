package health.model.service;

import health.model.entity.Recipe;
import health.model.entity.UserEntity;
import health.model.entity.enums.DietTargetEnum;

import java.util.List;

public class DietServiceModel {
    private String name;
    private UserEntity author;
    private DietTargetEnum target;
    private String description;


    public DietServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public DietTargetEnum getTarget() {
        return target;
    }

    public void setTarget(DietTargetEnum target) {
        this.target = target;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
