package health.model.service;

import health.model.entity.enums.TargetEnum;

public class TrainingServiceModel {
    private TargetEnum target;
    private String name;
    private String description;

    public TrainingServiceModel() {
    }

    public TargetEnum getTarget() {
        return target;
    }

    public TrainingServiceModel setTarget(TargetEnum target) {
        this.target = target;
        return this;
    }

    public String getName() {
        return name;
    }

    public TrainingServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TrainingServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
