package au.com.regimo.cms.repository;

import java.util.List;

import au.com.regimo.cms.domain.Article;
import au.com.regimo.core.repository.GenericRepository;

public interface ArticleRepository extends GenericRepository<Article> {

	List<Article> findBySlug(String slug);

	Article findByTitle(String title);

}
