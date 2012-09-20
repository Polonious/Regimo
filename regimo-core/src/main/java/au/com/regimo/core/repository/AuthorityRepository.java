package au.com.regimo.core.repository;

import java.util.List;

import au.com.regimo.core.domain.Authority;

public interface AuthorityRepository extends GenericRepository<Authority, Long> {

	List<Authority> findByNameStartsWith(String prefix);

	Authority findByName(String name);

}
