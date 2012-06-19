package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import au.com.regimo.core.domain.TextTemplate;
import au.com.regimo.core.domain.User;
import au.com.regimo.core.repository.GenericRepository;
import au.com.regimo.core.repository.TextTemplateRepository;
import au.com.regimo.core.repository.UserRepository;
import au.com.regimo.core.utils.RandomPassword;
import au.com.regimo.core.utils.TextGenerator;

@Named
public class UserService extends GenericService<User, Long>{

	private UserRepository repository;
	private PasswordEncoder passwordEncoder;
	private RowStatusService rowStatusService;
	private TextTemplateRepository textTemplateRepository;
	private EmailService emailService;
	
	@Transactional
	public User signup(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		rowStatusService.enable(user);
		repository.save(user);

		TextTemplate textTemplate = textTemplateRepository.findByName("WelcomeNewUser");
		if(textTemplate != null) {
			String emailBody = TextGenerator.generateText(textTemplate.getContent(), new ModelMap("user", user));
			emailService.sendEmail(user.getEmail(), "Regimo registration confirmation ", emailBody);
		}
		
		return user;
	}
	
	@Transactional
	public void resetPassword(User user) {
		 String tmpPwd = RandomPassword.getRandomString(6);
		user.setPassword(passwordEncoder.encode(tmpPwd));
		repository.save(user);
		emailService.sendEmail(user.getEmail(), "You have forgot your password, you reset the password  ", 
				", now reset to new password:" + tmpPwd + "\n Please change it ASAP");
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

	@Inject
	public void setTextTemplateRepository(TextTemplateRepository textTemplateRepository) {
		this.textTemplateRepository = textTemplateRepository;
	}

	@Inject
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

}
