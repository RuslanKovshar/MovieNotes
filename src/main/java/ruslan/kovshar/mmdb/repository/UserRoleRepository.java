package ruslan.kovshar.mmdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.kovshar.mmdb.model.Roles;
import ruslan.kovshar.mmdb.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRole(Roles role);
}
