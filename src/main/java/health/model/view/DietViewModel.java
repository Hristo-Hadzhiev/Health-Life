package health.model.view;

import health.model.entity.Recipe;
import health.model.entity.UserEntity;
import health.model.entity.enums.DietTargetEnum;

import java.util.List;

public class DietViewModel {

    private String id;
    private String name;
    private UserEntity author;
    private DietTargetEnum target;
    private int likes;
    private String description;

    public DietViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
