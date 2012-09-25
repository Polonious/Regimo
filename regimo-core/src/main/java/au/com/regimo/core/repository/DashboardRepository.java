package au.com.regimo.core.repository;

import au.com.regimo.core.domain.Dashboard;

public interface DashboardRepository extends GenericRepository<Dashboard>{

	Dashboard findByUserUsername(String username);
	
	Dashboard findByViewName(String viewName);
	
}
