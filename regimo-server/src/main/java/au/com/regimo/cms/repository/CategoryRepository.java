package au.com.regimo.cms.repository;

import java.util.List;

import au.com.regimo.cms.domain.Category;
import au.com.regimo.core.repository.GenericRepository;

public interface CategoryRepository extends GenericRepository<Category> {

	List<Category> findByName(String name);

	Category findBySlug(String slug);

}
