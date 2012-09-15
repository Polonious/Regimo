package au.com.regimo.web;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import au.com.regimo.core.domain.AuditLog;
import au.com.regimo.core.domain.AuditLogOperation;
import au.com.regimo.core.domain.Operator;
import au.com.regimo.core.domain.User;
import au.com.regimo.core.repository.AuditLogRepository;
import au.com.regimo.core.utils.StringUtils;

@Named
public class SigninSuccessHandler implements AuthenticationSuccessHandler {

	private AuthenticationSuccessHandler target = new SavedRequestAwareAuthenticationSuccessHandler();
	private AuditLogRepository auditLogRepository;
	private Integer timeout; // session timeout in minutes

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Operator operator = (Operator) authentication.getPrincipal();
		User user = operator.getUser();
		
		AuditLog auditLog = new AuditLog();
		auditLog.setOperation(AuditLogOperation.LOGIN);
		auditLog.setUser(user);
		auditLog.setTimestamp(new Date());
		auditLog.setEntityId(StringUtils.ipToLong(request.getRemoteAddr()));
		auditLog.setEntity(StringUtils.left(request.getHeader("user-agent"),255));
		auditLogRepository.save(auditLog);
		request.getSession().setMaxInactiveInterval(timeout*60);
		target.onAuthenticationSuccess(request, response, authentication);
	}

	public void proceed(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		target.onAuthenticationSuccess(request, response, authentication);
	}

	@Inject
	public void setAuditLogRepository(AuditLogRepository auditLogRepository) {
		this.auditLogRepository = auditLogRepository;
	}

	@Value("${session.timeout}")
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

}
