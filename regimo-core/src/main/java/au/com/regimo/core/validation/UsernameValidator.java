package au.com.regimo.core.validation;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import au.com.regimo.core.service.UserService;

public class UsernameValidator implements ConstraintValidator<Username, String> {

	private UserService userService;

	public void initialize(Username parameters) {}

	public boolean isValid(final String value, ConstraintValidatorContext context) {
		if(userService==null){
			return true;
		}
		return userService.findByUsername(value)==null;
	}

	@Inject
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}