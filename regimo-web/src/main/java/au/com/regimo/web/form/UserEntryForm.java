package au.com.regimo.web.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.social.connect.UserProfile;

import au.com.regimo.core.domain.User;
import au.com.regimo.core.utils.BeanUtilsExtend;
import au.com.regimo.core.validation.FieldMatch;
import au.com.regimo.core.validation.Username;
import au.com.regimo.web.form.validation.AddMode;

@FieldMatch(groups=AddMode.class, field="password", 
			match="confirmPassword", message="must match password")
public class UserEntryForm {

	private Long id;

	@NotEmpty(groups=AddMode.class)
	@Username(message="username exist")
	private String username;
	
	@Size(groups=AddMode.class, min=6, message="must be at least 6 characters")
	private String password;

	private String confirmPassword;
	
	private String firstName;

	private String lastName;

	@NotEmpty
	@Email
	private String email;

	public UserEntryForm() {}
	
	public UserEntryForm(User user) {
		BeanUtilsExtend.copyPropertiesWithoutNull(user, this, "password");
	}
	
	public UserEntryForm(UserProfile providerUser){
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
	
}
