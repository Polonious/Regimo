package au.com.regimo.core.repository;

import org.springframework.data.repository.CrudRepository;

import au.com.regimo.core.domain.Dashboard;

public interface DashboardRepository extends CrudRepository<Dashboard, Long>{

	Dashboard findByUserUsername(String username);
	
	Dashboard findByViewName(String viewName);
	
}
