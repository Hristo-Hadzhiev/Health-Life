package health.model.service;

import health.model.entity.UserEntity;
import health.model.entity.enums.TargetEnum;
import org.hibernate.validator.constraints.Length;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class DietServiceModel {
    private String name;
    private UserEntity author;
    private TargetEnum target;
    private String description;


    public DietServiceModel() {
    }

    @NotNull
    @Length(min = 3, message = "Diet name must be minimum 3 symbols.")
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

    @NotNull
    @Enumerated(EnumType.STRING)
    public TargetEnum getTarget() {
        return target;
    }

    public void setTarget(TargetEnum target) {
        this.target = target;
    }

    @Length(min = 10, message = "Description must be at least 10 chars.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
