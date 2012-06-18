package au.com.regimo.server.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;

import au.com.regimo.core.domain.TextTemplate;
import au.com.regimo.core.repository.TextTemplateRepository;
import au.com.regimo.core.service.EmailService;
import au.com.regimo.core.utils.TextGenerator;
import au.com.regimo.core.domain.User;

@Named
public class MessageService {

	@Value("${emailFrom}")private String emailFrom;

	private TextTemplateRepository textTemplateRepository;

	private EmailService emailService;
	
	public void signUpMessage(User user) {
		//send a signup confirmation email to the new user
		TextTemplate textTemplate = textTemplateRepository.findByName("WelcomeNewUser");
		ModelMap map = new ModelMap();
		map.addAttribute("user", user);
		
		String emailBody;
		if(textTemplate != null) {
			emailBody = TextGenerator.generateText(textTemplate.getContent(), map);
		}else{
			emailBody = "Regimo registration confirmation (Can not find email text template)";
		}
		emailService.sendEmail(emailFrom, user.getEmail(), "Regimo registration confirmation ", 
				null, emailBody, null, null);
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

