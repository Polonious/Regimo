package au.com.regimo.web.rest;

import javax.inject.Inject;

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
import au.com.regimo.web.form.UserEditForm;
import au.com.regimo.web.form.UserProfileEditForm;

@Controller
@RequestMapping(value="/rest/user")
public class RestUserController {

	private UserService userService;

	@RequestMapping(value="/profile", method=RequestMethod.GET)
	@ResponseBody
	public UserEditForm getProfile() {
		return new UserEditForm(SecurityUtils.getCurrentUser());
	}

	@RequestMapping(value="/profile", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void updateProfile(UserProfileEditForm form) {
		User user = userService.findOne(SecurityUtils.getCurrentUserId());
		BeanUtils.copyProperties(form, user);
		userService.save(user);
		SecurityUtils.updateCurrentUser(user);
	}

	@Inject
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
