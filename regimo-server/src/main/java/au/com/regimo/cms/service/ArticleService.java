package au.com.regimo.cms.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.cms.domain.Article;
import au.com.regimo.cms.repository.ArticleRepository;
import au.com.regimo.core.service.GenericService;

@Named
public class ArticleService extends GenericService<ArticleRepository, Article> {

	@Inject
	public ArticleService(ArticleRepository repository) {
		super(repository);
	}

	public Article findBySlug(String slug){
		return repository.findBySlug(slug);
	}

	public Article findByTitle(String title){
		return repository.findByTitle(title);
	}

}
