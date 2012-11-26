package au.com.regimo.cms.repository;

import java.util.List;

import au.com.regimo.cms.domain.Post;
import au.com.regimo.core.repository.GenericRepository;

public interface PostRepository extends GenericRepository<Post> {

	List<Post> findBySlug(String slug);

	Post findByTitle(String title);

}
