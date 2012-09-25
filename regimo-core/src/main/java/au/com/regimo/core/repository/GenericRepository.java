package au.com.regimo.core.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GenericRepository<T> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T> {

}
