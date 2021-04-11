package health.repository;

import health.model.entity.Training;
import health.model.entity.enums.TargetEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training,String> {

    Optional<Training> findByName(String name);

    List<Training> findAllByTarget(TargetEnum targetEnum);

}
