package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import au.com.regimo.core.domain.User;
import au.com.regimo.core.repository.GenericRepository;
import au.com.regimo.core.repository.UserRepository;
import au.com.regimo.core.utils.RandomPassword;


@Named
public class UserService extends GenericService<User, Long>{

	private UserRepository repository;
	private PasswordEncoder passwordEncoder;
	private RowStatusService rowStatusService;
	@Inject private EmailService emailService;
	@Value("${emailFrom}")private String emailFrom;
	
	@Transactional
	public User signup(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		rowStatusService.enable(user);
		repository.save(user);
		return user;
	}
	
	@Transactional
	public void resetPassword(User user) {
		 String tmpPwd = RandomPassword.getRandomString(6);
		user.setPassword(passwordEncoder.encode(tmpPwd));
		repository.save(user);
		emailService.sendEmail(emailFrom, user.getEmail(), "You have forgot your password, you reset the password  ", 
				null, ", now reset to new password:" + tmpPwd + "\n Please change it ASAP", null, null);
	}
	
	public User findByUsername(String username){
		return repository.findByUsername(username);
	}

	protected GenericRepository<User, Long> getRepository() {
		return repository;
	}

	@Inject
	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}
	
	@Inject
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Inject
	public void setRowStatusService(RowStatusService rowStatusService) {
		this.rowStatusService = rowStatusService;
	}

}
