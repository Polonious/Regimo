package au.com.regimo.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private FieldMatch constraint;

	public void initialize(final FieldMatch constraintAnnotation) {
		constraint = constraintAnnotation;
	}

	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		boolean isValid = false;
		try {
			final Object firstObj = BeanUtils.getProperty(value, constraint.first());
			final Object secondObj = BeanUtils.getProperty(value, constraint.second());
			isValid = firstObj == secondObj || firstObj != null && firstObj.equals(secondObj);
		} catch (final Exception ex) {
			logger.error("validation error", ex);
		}
		if(!isValid){
			context.buildConstraintViolationWithTemplate(constraint.message())
				.addNode(constraint.second()).addConstraintViolation();
		}
		return isValid;
	}
}