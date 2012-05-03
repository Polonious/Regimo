package au.com.regimo.core.domain;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;

public class Operator extends org.springframework.security.core.userdetails.User{

	private static final long serialVersionUID = 1L;

	private Date loginTime;
	
	private User user;
	
	private String logo="/resources/images/banner_standard.jpg";
	
	private String letterHeader;
	
	private String logoReport;

	public Operator(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities)
			throws IllegalArgumentException {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLetterHeader() {
		return letterHeader;
	}

	public void setLetterHeader(String letterHeader) {
		this.letterHeader = letterHeader;
	}
	
	public void setLogoReport(String logoReport) {
		this.logoReport = logoReport;
	}
	
	public String getLogoReport() {
		return this.logoReport;
	}

}

