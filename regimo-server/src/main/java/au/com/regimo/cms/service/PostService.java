package au.com.regimo.cms.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.cms.domain.Post;
import au.com.regimo.cms.repository.PostRepository;
import au.com.regimo.core.service.GenericService;

@Named
public class PostService extends GenericService<PostRepository, Post> {

	@Inject
	public PostService(PostRepository repository) {
		super(repository);
	}

}
