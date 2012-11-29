package au.com.regimo.server.wordpress.repository;

import java.util.List;

import au.com.regimo.server.wordpress.domain.WpTerm;

public interface WpTermRepository {

	List<WpTerm> findByTaxonomy(String taxonomy);
	List<WpTerm> findByTaxonomyCategory();
	WpTerm findBySlug(String slug);

}
