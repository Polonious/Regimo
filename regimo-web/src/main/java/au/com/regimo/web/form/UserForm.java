package au.com.regimo.web.form;

import org.springframework.beans.BeanUtils;
import org.springframework.social.connect.UserProfile;

import au.com.regimo.core.domain.User;

import com.google.common.base.Function;

public class UserForm implements Function<User, UserForm> {

	private Long id;

	private String username;

	private String firstName;

	private String lastName;

	private String email;

	public UserForm() {}

	public UserForm(User user) {
		BeanUtils.copyProperties(user, this);
	}

	public UserForm(UserProfile providerUser){
		firstName = providerUser.getFirstName();
		lastName = providerUser.getLastName();
		email = providerUser.getEmail();
	}

	public User getUpdatedUser(User user){
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public UserForm apply(User user) {
		return new UserForm(user);
	}

}
