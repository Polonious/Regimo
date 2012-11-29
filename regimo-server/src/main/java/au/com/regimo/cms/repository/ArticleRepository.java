package au.com.regimo.cms.repository;

import au.com.regimo.cms.domain.Article;
import au.com.regimo.core.repository.GenericRepository;

public interface ArticleRepository extends GenericRepository<Article> {

	Article findByTitle(String title);

	Article findBySlug(String slug);

}
