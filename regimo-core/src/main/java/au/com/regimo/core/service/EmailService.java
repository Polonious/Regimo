package au.com.regimo.core.service;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

@Named
public class EmailService {

	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	private JavaMailSender javaMailSender;
	private String emailFrom;
	private String emailTo;
	
	@Async
	public void sendEmail(String to,
			String subject, String body){
		sendEmail(emailFrom, to, subject, body, null, null, null);
	}
	
	@Async
	public void sendHtmlEmail(String to,
			String subject, String body){
		sendEmail(emailFrom, to, subject, null, body, null, null);
	}

	@Async
	public void sendEmail(String to,
			String subject, String body, String attachmentUrl){
		sendEmail(emailFrom, to, subject, body, null, attachmentUrl, null);
	}

	@Async
	public void sendEmail(String from, String to, String subject, 
			String body, String htmlBody, 
			String attachmentUrl, String attachmentName) {
		
		if(emailTo!=null && !emailTo.equals("")){
			subject += " ["+to+"]";
			to = emailTo;
		}

		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {

			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject(subject);
			String[] tos = to.split(",");
			helper.setTo(tos[0]);
			if(tos.length>1){
				for(String toa : tos){
					helper.addBcc(toa);
				}
			}
			
			helper.setFrom(from);
			
			if(body!=null && htmlBody!=null){
				helper.setText(body, htmlBody);
			}
			else if(htmlBody!=null){
				helper.setText(htmlBody,true);
			}
			else{
				helper.setText(body);
			}

			if(attachmentUrl!=null){
				File file = new File(attachmentUrl);
				if(file.exists()){
					String fileName = file.getName();
					if(attachmentName!=null){
						fileName = attachmentName;
					}
					helper.addAttachment(fileName, file);
				}
			}
			
			javaMailSender.send(message);

		} catch (MessagingException ex) {
			logger.error("Cannot send email. ",ex);
		}
	}

	@Inject
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Value("${mail.emailFrom.address}") 
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	@Value("${mail.emailTo.address}") 
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

}
