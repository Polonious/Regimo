package au.com.regimo.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import au.com.regimo.cms.domain.Category;
import au.com.regimo.core.repository.GenericRepository;

public interface CategoryRepository extends GenericRepository<Category> {

	List<Category> findByName(String name);

	@Query("select c.name from Category c where c.slug = ?1")
	String getNameBySlug(String slug);

}
