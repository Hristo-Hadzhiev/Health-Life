package health.model.binding;

import health.model.entity.enums.DietTargetEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class DietAddBindingModel {
    private String name;
    private String description;
    private DietTargetEnum target;

    public DietAddBindingModel() {
    }

    @NotNull
    @Length(min = 3, message = "Diet name must be minimum 3 symbols.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    public DietTargetEnum getTarget() {
        return target;
    }

    public void setTarget(DietTargetEnum target) {
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
