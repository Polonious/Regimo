package au.com.regimo.web.social;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import au.com.regimo.core.service.UserService;
import au.com.regimo.core.utils.SecurityUtils;

public final class SimpleSignInAdapter implements SignInAdapter {

	private RequestCache requestCache;

	private UserService userService;
	
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		SecurityUtils.setAuthentcation(userService.findOne(new Long(userId)));
		return extractOriginalUrl(request);
	}

	private String extractOriginalUrl(NativeWebRequest request) {
		HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);
		SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
		if (saved == null) {
			return null;
		}
		requestCache.removeRequest(nativeReq, nativeRes);
		removeAutheticationAttributes(nativeReq.getSession(false));
		return saved.getRedirectUrl();
	}

	private void removeAutheticationAttributes(HttpSession session) {
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	@Inject
	@Qualifier("org.springframework.security.web.savedrequest.HttpSessionRequestCache#0")
	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}

	@Inject
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
