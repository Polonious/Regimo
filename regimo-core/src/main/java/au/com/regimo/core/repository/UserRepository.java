package au.com.regimo.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import au.com.regimo.core.domain.User;

public interface UserRepository extends GenericRepository<User> {

	@Query("from User u where u.username = ?1 and u.rowStatus.current = true")
	User findByUsername(String username);

	@Query("from User u where u.email = ?1 and u.rowStatus.current = true")
	User findByEmail(String email);
	
	@Query("select u from User u where UPPER(u.username) like :text " +
			"or UPPER(u.firstName) like :text " +
			"or UPPER(u.lastName) like :text " +
			"or UPPER(u.email) like :text")
	Page<User> fullTextSearch(@Param("text") String text, Pageable pageable);

}
