package au.com.regimo.core.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import au.com.regimo.core.validation.AddMode;
import au.com.regimo.core.validation.FieldMatch;
import au.com.regimo.core.validation.Username;

@Entity
@Table(name="Users")
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_user")
@FieldMatch(groups=AddMode.class, field="password",
			match="confirmPassword", message="must match password")
public class User extends IdEntity implements IRowStatusAllowed, IAuditable {

	private static final long serialVersionUID = 1L;

    @Column(nullable = false)
	@NotEmpty(groups=AddMode.class)
	@Username(groups=AddMode.class, message="username exist")
    private String username;

    @Column(nullable = false)
	@Size(groups=AddMode.class, min=6, message="must be at least 6 characters")
    private String password;

    @Transient
	private String confirmPassword;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
	@NotEmpty
	@Email
	private String email;

    @OneToOne
	@JoinColumn(name="avatarId")
	private Document avatar;

	@ManyToOne
	@JoinColumn(name="rowStatusId")
    private RowStatus rowStatus;

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "UserRole", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public RowStatus getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(RowStatus rowStatus) {
		this.rowStatus = rowStatus;
	}

	public Document getAvatar() {
		return avatar;
	}

	public void setAvatar(Document avatar) {
		this.avatar = avatar;
	}

}
