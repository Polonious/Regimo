package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import au.com.regimo.core.domain.User;
import au.com.regimo.core.repository.GenericRepository;
import au.com.regimo.core.repository.UserRepository;

@Named
public class UserService extends GenericService<User, Long>{

	private UserRepository repository;
	private PasswordEncoder passwordEncoder;
	private RowStatusService rowStatusService;
	
	@Transactional
	public User signup(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		rowStatusService.enable(user);
		repository.save(user);
		return user;
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
