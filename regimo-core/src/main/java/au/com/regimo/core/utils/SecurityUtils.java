package au.com.regimo.core.utils;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import au.com.regimo.core.domain.Authority;
import au.com.regimo.core.domain.Operator;
import au.com.regimo.core.domain.Role;
import au.com.regimo.core.domain.User;

import com.google.common.collect.Sets;

public final class SecurityUtils {

	private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);
	
	private SecurityUtils() {}

	public static Long getCurrentUserId(){
		User user = getCurrentUser();
		if(user!=null){
			return user.getId();
		}
		return null;
	}

	public static User getCurrentUser(){
		User currentUser = null;
		
		Operator operator = getOperator();
		if(operator!=null){
			currentUser = operator.getUser();
		}
		
		return currentUser;
	}
	
	public static void updateCurrentUser(User user){
		getOperator().setUser(user);
	}

	public static Operator getOperator(){
		Operator operator = null;
		
		Authentication authentication = getAuthentication();
		if(authentication != null 
				&& authentication.getPrincipal() != null 
				&& authentication.getPrincipal() instanceof Operator){
			operator = (Operator) authentication.getPrincipal();
		}
		
		return operator;
	}

	public static boolean isUserInRole(String roleName) {
		String role = String.format("ROLE_%s", roleName);
		for (GrantedAuthority authority : getAuthorities()) {
			if (authority.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}
	
	public static Collection<? extends GrantedAuthority> getAuthorities() {
		Authentication authentication = getAuthentication();
		if(authentication!=null) {
			return authentication.getAuthorities();
		}
		return Sets.newHashSet();
	}
	
	public static String getCurrentUserIp() {
		String remoteAddress = "";
		
		Authentication authentication = getAuthentication();
		if (authentication != null) {
			Object details = authentication.getDetails();
			if (details instanceof WebAuthenticationDetails) {
				WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
				remoteAddress = webDetails.getRemoteAddress();
			}
		}

		return remoteAddress;
	}

	public static Operator createOperator(User user){
		return createOperator(user, user.getPassword());
	}
	
	public static Operator createOperator(User user, String encryptedPassword){
		
		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		Operator operator = new Operator(user.getUsername(), encryptedPassword, enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
		
		operator.setLoginTime(new Date());
		operator.setUser(user);
		
		return operator;
	}
	
	private static Authentication getAuthentication() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			logger.error("security context is empty, this seems to be a bug/misconfiguration!");
		}
		else{
			return context.getAuthentication();
		}
		return null;
	}
	
	public static void setAuthentcation(User user){
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			logger.error("security context is empty, this seems to be a bug/misconfiguration!");
		}
		else{
			Operator operator = createOperator(user);
			context.setAuthentication(new UsernamePasswordAuthenticationToken(
					operator, null, operator.getAuthorities()));
			
		}
	}
	
	public static void setAuthentcation(User user, String plainPassword){
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			logger.error("security context is empty, this seems to be a bug/misconfiguration!");
		}
		else{
			Operator operator = createOperator(user);
			context.setAuthentication(new UsernamePasswordAuthenticationToken(
					operator, plainPassword, operator.getAuthorities()));
		}
	}

	private static Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		if(user.getRoles()!=null){
			for (Role role : user.getRoles()) {
				authSet.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
				for (Authority authority : role.getAuthorities()) {
					authSet.add(new SimpleGrantedAuthority(authority.getName()));
				}
			}
		}
		return authSet;
	}
	
}
