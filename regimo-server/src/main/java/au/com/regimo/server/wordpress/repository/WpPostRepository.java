package au.com.regimo.server.wordpress.repository;

import java.util.List;

import au.com.regimo.server.wordpress.domain.WpPost;

public interface WpPostRepository {

	List<WpPost> findBySlug(String slug);
	
	WpPost findByPostName(String postName);
	
}
