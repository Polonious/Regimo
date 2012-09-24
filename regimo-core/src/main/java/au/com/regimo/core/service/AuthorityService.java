package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.core.domain.Authority;
import au.com.regimo.core.repository.AuthorityRepository;

@Named
public class AuthorityService extends GenericService<AuthorityRepository, Authority> {

	@Inject
	public AuthorityService(AuthorityRepository repository) {
		super(repository);
	}

}
