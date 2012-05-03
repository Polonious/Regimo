package au.com.regimo.core.repository;

import org.springframework.data.repository.CrudRepository;

import au.com.regimo.core.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
