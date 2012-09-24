package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.core.domain.Dashboard;
import au.com.regimo.core.repository.DashboardRepository;

@Named
public class DashboardService extends GenericService<DashboardRepository, Dashboard> {

	@Inject
	public DashboardService(DashboardRepository repository) {
		super(repository);
	}

}
