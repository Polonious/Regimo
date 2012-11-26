package au.com.regimo.cms.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.cms.domain.Category;
import au.com.regimo.cms.repository.CategoryRepository;
import au.com.regimo.core.service.GenericService;

@Named
public class CategoryService extends GenericService<CategoryRepository, Category> {

	@Inject
	public CategoryService(CategoryRepository repository) {
		super(repository);
	}

}
