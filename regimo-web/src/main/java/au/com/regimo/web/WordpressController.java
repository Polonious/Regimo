package au.com.regimo.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import au.com.regimo.core.repository.DashboardRepository;
import au.com.regimo.server.wordpress.repository.WpPostRepository;
import au.com.regimo.server.wordpress.repository.WpTermRepository;

@Controller
@RequestMapping("/wp")
public class WordpressController {

	private WpTermRepository wpTermRepository;
	private WpPostRepository wpPostRepository;
	private DashboardRepository dashboardRepository;
	
	@RequestMapping(value = "")
	public void browse(ModelMap map) {
		map.addAttribute("menu", wpTermRepository.findByTaxonomyCategory());
	}
	
	@RequestMapping(value = "/category/{slug}")
	public String getPostsByCategory(@PathVariable("slug") String slug, ModelMap map) {
		map.addAttribute("menu", dashboardRepository.findByViewName("HomeMenu"));
		map.addAttribute("posts", wpPostRepository.findBySlug(slug));
		return "posts";
	}

	@RequestMapping(value = "/post/{postName}")
	public String getPost(@PathVariable("postName") String postName, ModelMap map) {
		map.addAttribute("menu", dashboardRepository.findByViewName("HomeMenu"));
		map.addAttribute("post", wpPostRepository.findByPostName(postName));
		return "post";
	}

	@Inject
	public void setWpTermRepository(WpTermRepository wpTermRepository) {
		this.wpTermRepository = wpTermRepository;
	}
	
	@Inject
	public void setWpPostRepository(WpPostRepository wpPostRepository) {
		this.wpPostRepository = wpPostRepository;
	}

	@Inject
	public void setDashboardRepository(DashboardRepository dashboardRepository) {
		this.dashboardRepository = dashboardRepository;
	}

}
