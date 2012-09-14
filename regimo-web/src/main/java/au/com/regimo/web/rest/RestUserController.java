package au.com.regimo.web.rest;

import java.util.Collection;
import java.util.LinkedList;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import au.com.regimo.core.domain.User;
import au.com.regimo.core.service.UserService;
import au.com.regimo.core.utils.SecurityUtils;
import au.com.regimo.web.form.UserEntryForm;
import au.com.regimo.web.form.UserListForm;

@Controller
@RequestMapping(value="/rest/user")
public class RestUserController {

	private UserService userService;

	@RequestMapping(value="/profile", method=RequestMethod.GET)
	@ResponseBody
	public UserEntryForm getProfile() {
		return new UserEntryForm(SecurityUtils.getCurrentUser());
	}

	@RequestMapping(value="/profile", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void updateProfile(@Valid UserEntryForm form) {
		User user = userService.findOne(SecurityUtils.getCurrentUserId());
		BeanUtils.copyProperties(form, user);
		userService.save(user);
		SecurityUtils.updateCurrentUser(user);
	}

	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Collection<UserListForm> getUsers() {
		Collection<UserListForm> userList = new LinkedList<UserListForm>();
		for (User user : userService.findAll())
		{
			userList.add(new UserListForm(user));
		}
		return userList;
	}

	@Inject
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
