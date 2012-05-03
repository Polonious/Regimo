package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.core.domain.Dashlet;
import au.com.regimo.core.repository.DashletRepository;

@Named
public class DashletService extends GenericService<Dashlet, Long> {

	private DashletRepository repository;
	
	@Override
	protected DashletRepository getRepository() {
		return repository;
	}

	@Inject
	public void setRepository(DashletRepository repository) {
		this.repository = repository;
	}
	
	
}
