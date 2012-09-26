package au.com.regimo.web.rest;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import au.com.regimo.core.domain.User;
import au.com.regimo.core.service.DocumentService;
import au.com.regimo.core.service.UserService;
import au.com.regimo.core.utils.SecurityUtils;
import au.com.regimo.core.validation.AddMode;
import au.com.regimo.web.form.UserForm;

@Controller
@RequestMapping(value="/rest/user")
public class RestUserController {

	private UserService userService;
	private DocumentService documentService;

	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void signup(@Validated({Default.class, AddMode.class}) User user,
			@RequestParam(required=false) String image,
			BindingResult formBinding, HttpServletResponse response) throws IOException {
		if (formBinding.hasErrors()) {
			return ;
		}
		if(image!=null){
			user.setAvatar(documentService.saveBase64DecodeImage("userimage.jpg",
					image, "image/jpeg"));
		}
		user = userService.signup(user);
		response.addHeader("Location", user.getId().toString());
	}

	@RequestMapping(value="/profile", method=RequestMethod.GET)
	@ResponseBody
	public UserForm getProfile() {
		return new UserForm(SecurityUtils.getCurrentUser());
	}

	@RequestMapping(value="/profile", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void updateProfile(UserForm form) {
		User user = userService.findOne(SecurityUtils.getCurrentUserId());
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		user.setEmail(form.getEmail());
		userService.save(user);
		SecurityUtils.updateCurrentUser(user);
	}

	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Collection<UserForm> getUsers() {
		return userService.findAll(new UserForm());
	}

	@Inject
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
