package health.model.entity;

import health.model.entity.enums.DietTargetEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Table(name="diets")
public class Diet extends BaseClass{

    private String name;
    private UserEntity author;
    private DietTargetEnum target;
    private String description;
    private int likes;



    public Diet() {
    }

    public Diet(String name, UserEntity author, DietTargetEnum target, String description, int likes) {
        this.name = name;
        this.author = author;
        this.target = target;
        this.description = description;
        this.likes = likes;
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public Diet setName(String name) {
        this.name = name;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public Diet setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public DietTargetEnum getTarget() {
        return target;
    }

    public Diet setTarget(DietTargetEnum target) {
        this.target = target;
        return this;
    }

    @Column(name = "likes", nullable = false)
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = 0;
    }

    @Column(columnDefinition = "TEXT")
    @Length(min = 10, message = "Length must be at lest 10 chars.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
