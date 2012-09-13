package au.com.regimo.web.form;

import org.hibernate.validator.constraints.NotEmpty;

import au.com.regimo.core.domain.User;

// used by Admin updating user profile
public class UserEditForm extends UserProfileEditForm {

	@NotEmpty
	private String username;

	public UserEditForm(User user) {
		super(user);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
