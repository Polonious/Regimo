package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.core.domain.Authority;
import au.com.regimo.core.repository.AuthorityRepository;

@Named
public class AuthorityService extends GenericService<Authority, Long> {

	private AuthorityRepository repository;

	@Override
	protected AuthorityRepository getRepository() {
		return repository;
	}

	@Inject
	public void setRepository(AuthorityRepository repository) {
		this.repository = repository;
	}

}
