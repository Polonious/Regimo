package au.com.regimo.web;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import au.com.regimo.core.domain.AuditLog;
import au.com.regimo.core.domain.AuditLogOperation;
import au.com.regimo.core.domain.Operator;
import au.com.regimo.core.repository.AuditLogRepository;

@Named
public class SignoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

	@Inject private AuditLogRepository auditLogRepository;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String defaultTargetUrl = "/home";
		if(authentication!=null){
			Operator operator = (Operator) authentication.getPrincipal();
			if(operator!=null){
				if(operator.getUser()!=null){
					AuditLog auditLog = new AuditLog();
					auditLog.setOperation(AuditLogOperation.LOGOUT);
					auditLog.setUser(operator.getUser());
					auditLog.setTimestamp(new Date());
					auditLogRepository.save(auditLog);
				}
			}
		}
		this.setDefaultTargetUrl(defaultTargetUrl);
		this.setAlwaysUseDefaultTargetUrl(true);
		super.handle(request, response, authentication);
	}

}
