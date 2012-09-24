package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.core.domain.Dashlet;
import au.com.regimo.core.repository.DashletRepository;

@Named
public class DashletService extends GenericService<DashletRepository, Dashlet> {

	@Inject
	public DashletService(DashletRepository repository) {
		super(repository);
	}

}
