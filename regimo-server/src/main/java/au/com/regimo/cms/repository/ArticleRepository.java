package au.com.regimo.cms.repository;

import org.springframework.data.jpa.repository.Query;

import au.com.regimo.cms.domain.Article;
import au.com.regimo.core.repository.GenericRepository;

public interface ArticleRepository extends GenericRepository<Article> {

	@Query("select a from Article a inner join a.categories as c where c.slug = ?1 order by a.publishedDate desc")
	Iterable<Article> findByCategorySlug(String slug);

	Article findByTitle(String title);

	Article findBySlug(String slug);

}
