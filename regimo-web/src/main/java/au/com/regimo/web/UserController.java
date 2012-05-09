package au.com.regimo.web;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;

import au.com.regimo.core.domain.User;
import au.com.regimo.core.repository.UserRepository;
import au.com.regimo.core.service.GenericService;
import au.com.regimo.core.service.UserService;
import au.com.regimo.core.utils.BeanUtilsExtend;
import au.com.regimo.core.web.GenericEntityController;
import au.com.regimo.web.form.UserEditForm;
import au.com.regimo.web.form.UserListForm;
import au.com.regimo.web.form.UserNewForm;

@Controller
@RequestMapping(value="/user")
public class UserController extends GenericEntityController<User> {

	@Autowired
	private UserRepository userRepository;
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
	public String resetForgottenPwd(@ModelAttribute UserListForm form) {
	
		User user = userRepository.findByUsername(form.getUsername());
		if(user ==null){
		}
		else{
			entityService.resetPassword(user);
		}
		
		return "redirect:view/"+user.getId();
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
