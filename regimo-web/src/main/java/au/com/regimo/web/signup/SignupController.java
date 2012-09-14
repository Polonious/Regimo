package au.com.regimo.web.signup;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import au.com.regimo.core.domain.User;
import au.com.regimo.core.service.DocumentService;
import au.com.regimo.core.service.UserService;
import au.com.regimo.core.utils.BeanUtilsExtend;
import au.com.regimo.core.utils.SecurityUtils;
import au.com.regimo.web.form.UserEntryForm;
import au.com.regimo.web.form.validation.AddMode;

@Controller
public class SignupController {

	private UserService userService;
	private DocumentService documentService;
	
	/**
	 * Render a signup form to the person as HTML in their web browser.
	 */
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public UserEntryForm signupForm(WebRequest request) {
		return new UserEntryForm();
	}

	/**
	 * Process a signup form submission.
	 * Redirects the new member to the application home page on successful sign-in.
	 * @throws IOException 
	 */
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(@Validated({Default.class, AddMode.class}) UserEntryForm form, 
			BindingResult formBinding) {
		if (formBinding.hasErrors()) {
			return null;
		}
		User user = new User();
		BeanUtilsExtend.copyPropertiesWithoutNull(form, user);
		userService.signup(user);
		SecurityUtils.setAuthentcation(user, form.getPassword());
		return "redirect:/";
	}
	
	@RequestMapping(value="/rest/user", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void restSignup(@Validated({Default.class, AddMode.class}) UserEntryForm form, 
			@RequestParam(required=false) String image,
			BindingResult formBinding, HttpServletResponse response) throws IOException {
		if (formBinding.hasErrors()) {
			return ;
		}
		User user = new User();
		BeanUtilsExtend.copyPropertiesWithoutNull(form, user);
		if(image!=null){
			user.setImage(documentService.saveBase64DecodeImage("userimage.jpg", 
					image, "image/jpeg"));
		}
		userService.signup(user);		
		response.addHeader("Location", user.getId().toString());
	}

	@Inject
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Inject
	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}
	
}
