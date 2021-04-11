package health.repository;

import health.model.entity.Plan;
import health.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, String> {

    Optional<Plan> findByUser(UserEntity userEntity);

}
