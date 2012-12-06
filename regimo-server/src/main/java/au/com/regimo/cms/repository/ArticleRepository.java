package au.com.regimo.cms.repository;

import org.springframework.data.jpa.repository.Query;

import au.com.regimo.cms.domain.Article;
import au.com.regimo.core.repository.GenericRepository;

public interface ArticleRepository extends GenericRepository<Article> {

	@Query("from Article a where a.showOnFront=true")
	Iterable<Article> findAllShowOnFront();

	Article findByTitle(String title);

	Article findBySlug(String slug);

}
