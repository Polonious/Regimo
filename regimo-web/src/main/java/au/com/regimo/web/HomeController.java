package au.com.regimo.web;

import java.security.Principal;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.regimo.core.domain.Dashboard;
import au.com.regimo.core.domain.User;
import au.com.regimo.core.repository.DashboardRepository;
import au.com.regimo.core.service.UserService;
import au.com.regimo.core.utils.SecurityUtils;
import au.com.regimo.server.wordpress.repository.WpPostRepository;
import au.com.regimo.web.form.UserEditForm;
import au.com.regimo.web.form.UserProfileEditForm;
import au.com.regimo.web.form.UserListForm;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject private UserService userService;
	private DashboardRepository dashboardRepository;
	private WpPostRepository wpPostRepository;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap map, Principal user) {
		logger.info("Welcome home! ");
		if(user!=null){
			return "redirect:/profile/view";
		}
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home1(ModelMap map) {
		logger.info("Welcome home! ");
		Dashboard menu = dashboardRepository.findByViewName("HomeMenu");
		map.addAttribute("menu", menu);
		Dashboard content = dashboardRepository.findByViewName("HomeContent");
		map.addAttribute("content", content);
		return "home";
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public void signin(ModelMap map) {
		Dashboard menu = dashboardRepository.findByViewName("HomeMenu");
		map.addAttribute("menu", menu);
	}
	
	@RequestMapping(value="/profile/view", method=RequestMethod.GET)
	public void profile(ModelMap map) {
		Dashboard menu = dashboardRepository.findByViewName("HomeMenu");
		map.addAttribute("menu", menu);
		map.addAttribute("user", SecurityUtils.getCurrentUser());
	}
	
	@RequestMapping(value="/profile/edit", method=RequestMethod.GET)
	public void profile_edit(ModelMap map) {
		Dashboard menu = dashboardRepository.findByViewName("HomeMenu");
		map.addAttribute("menu", menu);
		map.addAttribute("user", SecurityUtils.getCurrentUser());
	}
	
	@RequestMapping(value="/contact-us", method=RequestMethod.GET)
	public String contactUs(ModelMap map) {
		Dashboard menu = dashboardRepository.findByViewName("HomeMenu");
		map.addAttribute("post", wpPostRepository.findByPostName("contact-us"));
		map.addAttribute("menu", menu);
		return "post";
	}

	@Inject
	public void setDashboardRepository(DashboardRepository dashboardRepository) {
		this.dashboardRepository = dashboardRepository;
	}
	
	@Inject
	public void setWpPostRepository(WpPostRepository wpPostRepository) {
		this.wpPostRepository = wpPostRepository;
	}
	
	@RequestMapping(value = "/userUpdateCommit", method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute UserProfileEditForm form,  ModelMap map) {
		User user = userService.findOne(SecurityUtils.getCurrentUserId());
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		userService.save(user);
		SecurityUtils.updateCurrentUser(user);
		return "redirect:/profile/view";
	}	
}
