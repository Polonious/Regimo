package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.core.domain.Dashboard;
import au.com.regimo.core.repository.DashboardRepository;

@Named
public class DashboardService extends GenericService<Dashboard, Long> {

	private DashboardRepository repository;
	
	@Override
	protected DashboardRepository getRepository() {
		return repository;
	}

	@Inject
	public void setRepository(DashboardRepository repository) {
		this.repository = repository;
	}
	
	
}
