package au.com.regimo.core.repository;

import java.util.List;

import au.com.regimo.core.domain.RowStatus;

public interface RowStatusRepository extends GenericRepository<RowStatus, Long> {

	List<RowStatus> findByStatusObject(String statusObject);

	RowStatus findByReferenceAndStatusObject(String reference, String statusObject);

}
