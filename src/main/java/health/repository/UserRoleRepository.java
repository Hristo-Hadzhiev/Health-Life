package health.repository;

import health.model.entity.UserRoleEntity;
import health.model.entity.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {

    UserRoleEntity findByRole(UserRole admin);
}
