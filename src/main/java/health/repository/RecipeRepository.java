package health.repository;

import health.model.entity.Recipe;
import health.model.entity.UserEntity;
import health.model.entity.enums.RecipeEnum;
import health.model.entity.enums.TargetEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {

    List<Recipe> findAllByTypeOfRecipe(RecipeEnum type);

    List<Recipe> findAllByTarget(TargetEnum targetEnum);

    Optional<Recipe> findById(String id);

    Optional<Recipe> findByName(String name);

}
