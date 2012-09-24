package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.core.domain.Role;
import au.com.regimo.core.repository.RoleRepository;

@Named
public class RoleService extends GenericService<RoleRepository, Role> {

	@Inject
	public RoleService(RoleRepository repository) {
		super(repository);
	}

}
