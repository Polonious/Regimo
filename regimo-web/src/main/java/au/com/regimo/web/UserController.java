package au.com.regimo.web;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.groups.Default;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import au.com.regimo.core.domain.User;
import au.com.regimo.core.form.DataTablesSearchCriteria;
import au.com.regimo.core.form.TransformRequired;
import au.com.regimo.core.service.UserService;
import au.com.regimo.core.utils.BeanUtilsExtend;
import au.com.regimo.web.form.UserEntryForm;
import au.com.regimo.web.form.UserListForm;
import au.com.regimo.web.form.validation.AddMode;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/user")
public class UserController implements TransformRequired<User> {

	private UserService service;

	@RequestMapping(method=RequestMethod.GET)
	public String browse(ModelMap modelMap) {
		return service.loadModelName(modelMap);
	}

	@RequestMapping(method=RequestMethod.POST)
	public void browse(ModelMap modelMap,
			@ModelAttribute DataTablesSearchCriteria searchCriteria){
		service.searchFullText(searchCriteria, modelMap, this);
	}

	@Override
	public List<?> getMappedSearchResult(List<User> result){
		List<UserListForm> list = Lists.newLinkedList();
		for(User user : result){
			list.add(new UserListForm(user));
		}
		return list;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void view(@RequestParam Long id, ModelMap modelMap) {
		service.loadModel(modelMap, id);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public void create(ModelMap modelMap) {
		service.loadModel(modelMap);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(@Validated({Default.class, AddMode.class}) UserEntryForm form,
			BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		User user = new User();
		BeanUtilsExtend.copyPropertiesWithoutNull(form, user);
		service.signup(user);
		return "redirect:view?id="+user.getId();
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public void update(@RequestParam Long id, ModelMap modelMap) {
		service.loadModel(modelMap, id);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid UserEntryForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		User user = service.findOne(form.getId());
		service.save(form.getUpdatedUser(user));
		return "redirect:view?id="+form.getId();
	}

	@RequestMapping(value = "/resetForgottenPwd", method = RequestMethod.POST)
	public String resetForgottenPwd(@RequestParam String username, ModelMap modelMap) {
		User user = service.findByUsername(username);
		if(user != null){
			service.resetPassword(user);
			modelMap.addAttribute("resetPwdSuccessfully", "true");
		}
		else{
			modelMap.addAttribute("resetPwdSubscribeFailed", "true");
		}
		return "signin";
	}

	@Inject
	public void setUserService(UserService service) {
		this.service = service;
	}


}
