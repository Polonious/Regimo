package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import au.com.regimo.core.domain.User;
import au.com.regimo.core.repository.UserRepository;
import au.com.regimo.core.utils.SecurityUtils;
import au.com.regimo.core.utils.StringUtils;

@Transactional(readOnly = true)
@Named("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Inject
	public UserDetailsServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		if(StringUtils.isEmail(username)){
			user = userRepository.findByEmail(username);
		}
		else{
			user = userRepository.findByUsername(username);
		}
		
		if(user==null){
			throw new UsernameNotFoundException("Username/Email not found: " + username);
		}

		return SecurityUtils.createOperator(user);
	}

}
