package health.model.entity;

import health.model.entity.enums.TargetEnum;

import javax.persistence.*;

@Entity
@Table(name = "trainings")
public class Training extends BaseClass{

    private TargetEnum target;
    private String name;
    private String description;
    private UserEntity author;

    public Training() {
    }

    @Column
    @Enumerated(EnumType.STRING)
    public TargetEnum getTarget() {
        return target;
    }

    public Training setTarget(TargetEnum target) {
        this.target = target;
        return this;
    }

    @Column(name = "training_name")
    public String getName() {
        return name;
    }

    public Training setName(String trainingName) {
        this.name = trainingName;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Training setDescription(String description) {
        this.description = description;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public Training setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
