package health.model.binding;

import health.model.entity.enums.TargetEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class TrainingAddBindingModel {

    private TargetEnum target;
    private String trainingName;
    private String description;

    public TrainingAddBindingModel() {
    }

    @Enumerated(EnumType.STRING)
    public TargetEnum getTarget() {
        return target;
    }


    public TrainingAddBindingModel setTarget(TargetEnum target) {
        this.target = target;
        return this;
    }

    @Length(min = 3, message = "Name must be at least 3 characters.")
    public String getTrainingName() {
        return trainingName;
    }

    public TrainingAddBindingModel setTrainingName(String trainingName) {
        this.trainingName = trainingName;
        return this;
    }

    @Length(min = 10, message = "Description must be at least 10 characters")
    public String getDescription() {
        return description;
    }

    public TrainingAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
