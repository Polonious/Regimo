package au.com.regimo.core.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=UsernameValidator.class)
public @interface Username {
	String message() default "Username already exists";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}