package au.com.regimo.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import au.com.regimo.core.domain.RowStatus;

public interface RowStatusRepository extends CrudRepository<RowStatus, Long> {

	List<RowStatus> findByStatusObject(String statusObject);
	
	RowStatus findByReferenceAndStatusObject(String reference, String statusObject);
	
}
