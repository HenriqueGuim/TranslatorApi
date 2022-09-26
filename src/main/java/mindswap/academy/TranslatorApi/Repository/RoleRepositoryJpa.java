package mindswap.academy.TranslatorApi.Repository;

import mindswap.academy.TranslatorApi.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface RoleRepositoryJpa extends JpaRepository<Role, Long> {
    Role findRoleByTypeRole(String role);
}

