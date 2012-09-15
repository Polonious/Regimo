package au.com.regimo.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
	
	private FieldMatch constraint;

	public void initialize(final FieldMatch constraintAnnotation) {
		constraint = constraintAnnotation;
	}

	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		boolean isValid = false;
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		final Object field = beanWrapper.getPropertyValue(constraint.field());
		final Object match = beanWrapper.getPropertyValue(constraint.match());
		isValid = field == match || field != null && field.equals(match);
		if(!isValid){
			context.buildConstraintViolationWithTemplate(constraint.message())
				.addNode(constraint.match()).addConstraintViolation();
		}
		return isValid;
	}
}