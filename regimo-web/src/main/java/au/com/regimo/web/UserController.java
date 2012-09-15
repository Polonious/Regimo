package au.com.regimo.web;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.groups.Default;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import au.com.regimo.core.domain.User;
import au.com.regimo.core.service.GenericService;
import au.com.regimo.core.service.UserService;
import au.com.regimo.core.utils.BeanUtilsExtend;
import au.com.regimo.core.web.GenericEntityController;
import au.com.regimo.web.form.UserEntryForm;
import au.com.regimo.web.form.UserListForm;
import au.com.regimo.web.form.validation.AddMode;

import com.google.common.collect.Lists;

@Controller
@RequestMapping(value="/user")
public class UserController extends GenericEntityController<User> {

	private UserService entityService;
	
	@Override
	protected List<?> getMappedSearchResult(List<User> result){
		List<UserListForm> list = Lists.newLinkedList();
		for(User user : result){
			list.add(new UserListForm(user));
		}
		return list;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(@Validated({Default.class, AddMode.class}) UserEntryForm form, 
			BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		User user = new User();
		BeanUtilsExtend.copyPropertiesWithoutNull(form, user);
		entityService.signup(user);
		return "redirect:view/"+user.getId();
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid UserEntryForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		User user = entityService.findOne(form.getId());
		entityService.save(form.getUpdatedUser(user));
		return "redirect:/user/view/"+form.getId();
	}

	@RequestMapping(value = "/resetForgottenPwd", method = RequestMethod.POST)
	public String resetForgottenPwd(@RequestParam String username, ModelMap modelMap) {
		User user = entityService.findByUsername(username);
		if(user != null){
			entityService.resetPassword(user);
			modelMap.addAttribute("resetPwdSuccessfully", "true");
		}
		else{
			modelMap.addAttribute("resetPwdSubscribeFailed", "true");
		}
		return "signin";
	}
	
	@Inject
	public void setUserService(UserService entityService) {
		this.entityService = entityService;
	}

	@Override
	protected GenericService<User, Long> getEntityService() {
		return entityService;
	}

}
