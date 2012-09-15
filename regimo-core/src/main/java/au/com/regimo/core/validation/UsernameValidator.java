package au.com.regimo.core.validation;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import au.com.regimo.core.service.UserService;

public class UsernameValidator implements ConstraintValidator<Username, Object> {

	private UserService userService;
	
	public void initialize(Username parameters) {}

	public boolean isValid(final Object value, ConstraintValidatorContext context) {
		return userService.findByUsername((String)value)==null;
	}

	@Inject
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}