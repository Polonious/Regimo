package au.com.regimo.core.repository;

import au.com.regimo.core.domain.Role;

public interface RoleRepository extends GenericRepository<Role> {

	Role findByName(String rolename);

}
