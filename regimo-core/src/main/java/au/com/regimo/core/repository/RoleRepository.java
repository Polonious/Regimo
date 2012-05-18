package au.com.regimo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import au.com.regimo.core.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String rolename);
}
