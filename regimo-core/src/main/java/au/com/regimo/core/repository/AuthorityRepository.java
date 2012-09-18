package au.com.regimo.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import au.com.regimo.core.domain.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {

	List<Authority> findByNameStartsWith(String prefix);
	
}
