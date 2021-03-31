package health.model.service;

import health.model.entity.Diet;
import health.model.entity.Recipe;
import health.model.entity.UserRoleEntity;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel {
    private String username;
    private String password;
    private String email;
    private List<UserRoleEntity> roles = new ArrayList<>();
    private List<Diet> createdDiets;
    private List<Recipe> createdRecipes;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
    }

    public List<Diet> getCreatedDiets() {
        return createdDiets;
    }

    public void setCreatedDiets(List<Diet> createdDiets) {
        this.createdDiets = createdDiets;
    }

    public List<Recipe> getCreatedRecipes() {
        return createdRecipes;
    }

    public void setCreatedRecipes(List<Recipe> createdRecipes) {
        this.createdRecipes = createdRecipes;
    }
}
