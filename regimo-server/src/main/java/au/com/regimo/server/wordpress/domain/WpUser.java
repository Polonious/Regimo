package au.com.regimo.server.wordpress.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the wp_users database table.
 * 
 */
@Entity
@Table(name="wp_users")
public class WpUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;

	@Column(name="display_name")
	private String displayName;

	@Column(name="user_activation_key")
	private String userActivationKey;

	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_login")
	private String userLogin;

	@Column(name="user_nicename")
	private String userNicename;

	@Column(name="user_pass")
	private String userPass;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="user_registered")
	private Date userRegistered;

	@Column(name="user_status")
	private int userStatus;

	@Column(name="user_url")
	private String userUrl;

	//bi-directional many-to-one association to WpUsermeta
	@OneToMany(mappedBy="wpUser")
	private Set<WpUsermeta> wpUsermetas;

	//bi-directional many-to-one association to WpPost
	@OneToMany(mappedBy="wpUser")
	private Set<WpPost> wpPosts;

    public WpUser() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getUserActivationKey() {
		return this.userActivationKey;
	}

	public void setUserActivationKey(String userActivationKey) {
		this.userActivationKey = userActivationKey;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserLogin() {
		return this.userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserNicename() {
		return this.userNicename;
	}

	public void setUserNicename(String userNicename) {
		this.userNicename = userNicename;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public Date getUserRegistered() {
		return this.userRegistered;
	}

	public void setUserRegistered(Date userRegistered) {
		this.userRegistered = userRegistered;
	}

	public int getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserUrl() {
		return this.userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public Set<WpUsermeta> getWpUsermetas() {
		return this.wpUsermetas;
	}

	public void setWpUsermetas(Set<WpUsermeta> wpUsermetas) {
		this.wpUsermetas = wpUsermetas;
	}
	
	public Set<WpPost> getWpPosts() {
		return this.wpPosts;
	}

	public void setWpPosts(Set<WpPost> wpPosts) {
		this.wpPosts = wpPosts;
	}
	
}