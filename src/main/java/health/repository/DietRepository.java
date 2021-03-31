package health.repository;

import health.model.entity.Diet;
import health.model.entity.Recipe;
import health.model.entity.enums.DietTargetEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DietRepository extends JpaRepository<Diet, String> {

    List<Diet> findAllByTarget(DietTargetEnum dietTargetEnum);

    Optional<Diet> findByName(String name);

}
