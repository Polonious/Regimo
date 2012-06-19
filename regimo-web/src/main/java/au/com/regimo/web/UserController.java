package au.com.regimo.web;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.regimo.core.domain.User;
import au.com.regimo.core.service.GenericService;
import au.com.regimo.core.service.UserService;
import au.com.regimo.core.utils.BeanUtilsExtend;
import au.com.regimo.core.web.GenericEntityController;
import au.com.regimo.web.form.UserEditForm;
import au.com.regimo.web.form.UserListForm;
import au.com.regimo.web.form.UserNewForm;

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
	public String create(@Valid @ModelAttribute UserNewForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		User user = new User();
		BeanUtilsExtend.copyPropertiesWithoutNull(form, user, "image");
		entityService.signup(user);
		return "redirect:view/"+user.getId();
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute UserEditForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		User user = entityService.findOne(form.getId());
		BeanUtilsExtend.copyPropertiesWithoutNull(form, user, "image");
		entityService.save(user);
		return "redirect:/user/view/"+form.getId();
	}

	@Transactional
	@RequestMapping(value = "/resetForgottenPwd", method = RequestMethod.POST)
	public String resetForgottenPwd(@ModelAttribute UserListForm form, ModelMap modelMap) {
		User user = entityService.findByUsername(form.getUsername());
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
