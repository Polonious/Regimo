package au.com.regimo.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.regimo.core.domain.Dashboard;
import au.com.regimo.core.domain.UserDashlet;
import au.com.regimo.core.repository.DashboardRepository;
import au.com.regimo.core.repository.UserDashletRepository;
import au.com.regimo.core.utils.SecurityUtils;
import au.com.regimo.core.utils.TextGenerator;
import au.com.regimo.server.wordpress.domain.WpTerm;
import au.com.regimo.server.wordpress.repository.WpPostRepository;
import au.com.regimo.server.wordpress.repository.WpTermRepository;
import au.com.regimo.web.spring.UrlVoter;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	private DashboardRepository dashboardRepository;
	private UserDashletRepository userDashletRepository;
	private WpTermRepository wpTermRepository;
	private WpPostRepository wpPostRepository;
	private UrlVoter urlVoter;
	
	@RequestMapping(value = "")
	public void dashboard(ModelMap map) {
		Dashboard dashboard = dashboardRepository.findByViewName("dashboard");
		map.addAttribute("dashboard", dashboard);
	}
	
	@RequestMapping(value = "/{userDashletId}")
	public @ResponseBody String browse(@PathVariable("userDashletId") Long userDashletId, 
			ModelMap map) {
		UserDashlet userDashlet = userDashletRepository.findOne(userDashletId);
		String dashModel = userDashlet.getDashlet().getModel();
		if(dashModel!=null){
			if("wpTerms".equals(dashModel)){
				map.addAttribute("wpTerms", wpTermRepository.findByTaxonomyCategory());
			}
			else if("wpPosts".equals(dashModel)){
				map.addAttribute("wpPosts", wpPostRepository.findBySlug(
						userDashlet.getDashlet().getParameter()));
			}
			else if("wpPost".equals(dashModel)){
				map.addAttribute("wpPost", wpPostRepository.findByPostName(
						userDashlet.getDashlet().getParameter()));
			}
			else if("wpMenu".equals(dashModel)){
				List<WpTerm> terms = wpTermRepository.findByTaxonomyCategory();
				for(WpTerm term : terms){
					term.setWpPosts(wpPostRepository.findBySlug(term.getSlug()));
				}
				map.addAttribute("wpMenu", terms);
			}
			else{
				return "TODO: Freemarkered content "+ userDashlet.getDashlet().getContent();
			}
		}
		map.addAttribute("user", SecurityUtils.getCurrentUser());
		map.addAttribute("security", urlVoter);
		return TextGenerator.generateText(String.format(
				"[#macro url link][#if security.isAuthorised(link)][#nested link][/#if][/#macro]%s%s",
				"[#macro acl attribute][#if security.isPermit(attribute)][#nested][/#if][/#macro]",
				userDashlet.getDashlet().getContent()), map);
	}

	@Inject
	public void setDashboardRepository(DashboardRepository dashboardRepository) {
		this.dashboardRepository = dashboardRepository;
	}

	@Inject
	public void setUserDashletRepository(UserDashletRepository userDashletRepository) {
		this.userDashletRepository = userDashletRepository;
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
	public void setUrlVoter(UrlVoter urlVoter) {
		this.urlVoter = urlVoter;
	}

}
