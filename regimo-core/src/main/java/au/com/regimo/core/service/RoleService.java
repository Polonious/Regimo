package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.core.domain.Role;
import au.com.regimo.core.repository.RoleRepository;

@Named
public class RoleService extends GenericService<Role, Long> {

	private RoleRepository repository;

	@Override
	protected RoleRepository getRepository() {
		return repository;
	}

	@Inject
	public void setRepository(RoleRepository repository) {
		this.repository = repository;
	}

}
