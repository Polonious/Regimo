package au.com.regimo.core.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validation annotation to validate that 2 fields have the same value. An array
 * of fields and their matching confirmation fields can be supplied.
 * 
 * Example, compare 1 pair of fields:
 * 
 * @FieldMatch(field = "password", match = "confirmPassword", message =
 *                   "The password fields must match")
 * 
 *                   Example, compare more than 1 pair of fields:
 * @FieldMatch.List({
 * @FieldMatch(field = "password", match = "confirmPassword", message =
 *                   "The password fields must match"),
 * @FieldMatch(field = "email", match = "confirmEmail", message =
 *                   "The email fields must match")})
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FieldMatchValidator.class)
@Documented
public @interface FieldMatch {
	String message() default "{validator.fieldmatch}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	/**
	 * @return The first field
	 */
	String field();

	/**
	 * @return The second field
	 */
	String match();

	/**
	 * Defines several <code>@FieldMatch</code> annotations on the same element
	 * 
	 * @see FieldMatch
	 */
	@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		FieldMatch[] value();
	}
}