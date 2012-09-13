package au.com.regimo.web.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import au.com.regimo.core.domain.User;
import au.com.regimo.core.utils.BeanUtilsExtend;

// used by User updating their own profile
public class UserProfileEditForm {

	protected Long id;

	protected String firstName;

	protected String lastName;

	@NotEmpty
	@Email
	protected String email;

	protected String confirmEmail;

	public UserProfileEditForm(User user) {
		this();
		BeanUtilsExtend.copyPropertiesWithoutNull(user, this);
	}

	public UserProfileEditForm() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

}
