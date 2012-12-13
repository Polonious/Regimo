package au.com.regimo.web;

import java.security.Principal;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.groups.Default;

import org.springframework.beans.BeanUtils;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import au.com.regimo.cms.service.ArticleService;
import au.com.regimo.core.domain.Dashboard;
import au.com.regimo.core.domain.User;
import au.com.regimo.core.repository.DashboardRepository;
import au.com.regimo.core.service.UserService;
import au.com.regimo.core.utils.SecurityUtils;
import au.com.regimo.core.validation.AddMode;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private UserService userService;
	private DashboardRepository dashboardRepository;
	private ArticleService articleService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap map, Principal user, WebRequest request) {
		if(user!=null && !SecurityUtils.isUserInRole("ADMIN")){
			ProviderSignInUtils.handlePostSignUp(SecurityUtils.getCurrentUserId().toString(), request);
			return "redirect:/profile";
		}
		return home(map);
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(ModelMap map) {
		Dashboard content = dashboardRepository.findByViewName("HomeContent");
		map.addAttribute("content", content);
		map.addAttribute("feature", articleService.findAllFeatured());
		return "home";
	}

	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public void signin() {
	}

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public User signup(WebRequest request) {
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		User user = new User();
		if (connection != null) {
			BeanUtils.copyProperties(connection.fetchUserProfile(), user);
		}
		return user;
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(WebRequest request,
			@Validated({Default.class, AddMode.class}) User user, BindingResult formBinding) {
		if (formBinding.hasErrors()) {
			return null;
		}
		String password = user.getPassword();
		user = userService.signup(user);
		SecurityUtils.setAuthentcation(user, password);
		ProviderSignInUtils.handlePostSignUp(user.getId().toString(), request);
		return "redirect:/home";
	}

	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public void viewProfile(ModelMap map) {
		map.addAttribute("user",  SecurityUtils.getCurrentUser());
	}

	@RequestMapping(value="/profile/edit", method=RequestMethod.GET)
	public void editProfile(ModelMap map) {
		map.addAttribute("user", SecurityUtils.getCurrentUser());
	}

	@RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
	public String updateProfile(ModelMap modelMap, @Valid User model, BindingResult binding) {
		if (binding.hasErrors()) {
			return null;
		}
		User user = SecurityUtils.getCurrentUser();
		user.setFirstName(model.getFirstName());
		user.setLastName(model.getLastName());
		user.setEmail(model.getEmail());
		user = userService.save(user);
		SecurityUtils.updateCurrentUser(user);
		return "redirect:/profile";
	}

	@RequestMapping(value = "/contents")
	public void dashboard(ModelMap map) {
		Dashboard dashboard = dashboardRepository.findByViewName("dashboard");
		map.addAttribute("dashboard", dashboard);
	}

	@Inject
	public void setDashboardRepository(DashboardRepository dashboardRepository) {
		this.dashboardRepository = dashboardRepository;
	}

	@Inject
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Inject
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

}
