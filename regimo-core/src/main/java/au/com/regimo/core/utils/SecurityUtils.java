package au.com.regimo.core.utils;

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
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.google.common.collect.Sets;

import au.com.regimo.core.domain.Authority;
import au.com.regimo.core.domain.Operator;
import au.com.regimo.core.domain.Role;
import au.com.regimo.core.domain.User;

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
			context.setAuthentication(new UsernamePasswordAuthenticationToken(
					createOperator(user, "de93af5de0169e52bbeea8afbbd4b4e0"), "polonious"));
			
		}
	}
	
	public static void setAuthentcation(User user, String plainPassword){
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			logger.error("security context is empty, this seems to be a bug/misconfiguration!");
		}
		else{
			context.setAuthentication(new UsernamePasswordAuthenticationToken(
					createOperator(user), plainPassword));
		}
	}

	private static Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		if(user.getRoles()!=null){
			for (Role role : user.getRoles()) {
				authSet.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
				for (Authority authority : role.getAuthorities()) {
					authSet.add(new SimpleGrantedAuthority(authority.getValue()));
				}
			}
		}
		return authSet;
	}
	
	public static boolean isAuthorizedWithBasicAuthentication(String auth, String username, String password){
		boolean authorized = false;
		if (auth.startsWith("Basic ")) {
            byte[] base64Token = auth.substring(6).getBytes();
            String token = new String(Base64.decode(base64Token));

            String auth_username = "";
            String auth_password = "";
            int delim = token.indexOf(":");

            if (delim != -1) {
            	auth_username = token.substring(0, delim);
            	auth_password = token.substring(delim + 1);
            }
		
			if(auth_username.equals(username) && auth_password.equals(password)){
				authorized = true;
			}
		}
		return authorized;
	}
	
}
