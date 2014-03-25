package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.core.domain.UserDashlet;
import au.com.regimo.core.repository.UserDashletRepository;

@Named
public class UserDashletService extends GenericService<UserDashletRepository, UserDashlet> {

	@Inject
	public UserDashletService(UserDashletRepository repository) {
		super(repository);
	}

}
