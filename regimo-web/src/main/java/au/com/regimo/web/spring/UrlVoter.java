package au.com.regimo.web.spring;

import java.util.Collection;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import com.google.common.collect.Maps;

import au.com.regimo.core.domain.Authority;
import au.com.regimo.core.repository.AuthorityRepository;
import au.com.regimo.core.utils.SecurityUtils;

@Named
public class UrlVoter implements AccessDecisionVoter<FilterInvocation> {

	private static final Logger logger = LoggerFactory.getLogger(UrlVoter.class);

	private String prefix = "URL_";

	private Map<String, String> authorityMaps; // Map<authority, url>
	private Map<String, String> urlMaps; // Map<url, authority>
	private Map<RequestMatcher, String> urlMatchers;

	private AuthorityRepository authorityRepository;

	@Inject
    public UrlVoter(AuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
		loadUrls();
	}

    public boolean supports(ConfigAttribute attribute) {
    	return attribute.getAttribute() != null
    			&& attribute.getAttribute().startsWith(prefix);
    }

    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(FilterInvocation.class);
    }

	public int vote(Authentication authentication, FilterInvocation fi,
			Collection<ConfigAttribute> attributes) {
    	String attribute = getAttribute(fi.getRequest());
        return attribute==null ? ACCESS_ABSTAIN :
        	isAuthorized(attribute, authentication.getAuthorities()) ?
				ACCESS_GRANTED : ACCESS_DENIED;
    }

	public String getAuthority(String url, String method){
		return getAttribute(new FilterInvocation(url, method).getRequest());
	}

	public String getAuthorizedUrl(String attribute){
		if(!attribute.startsWith(prefix)) attribute = prefix + attribute;
		if(isAuthorized(attribute)){
			String url = authorityMaps.get(attribute);
			if(url!=null) return url;
		}
		return "";
	}

	public boolean isAuthorizedUrl(String url){
		String attribute = getAuthority(url, "get");
		return attribute==null ? false : isAuthorized(attribute);
	}

	public boolean isAuthorized(String attribute){
		return isAuthorized(attribute, SecurityUtils.getAuthorities());
	}

	private boolean isAuthorized(String attribute, Collection<? extends GrantedAuthority> authorities){
		// Attempt to find a matching granted authority
		for (GrantedAuthority authority : authorities) {
            if (attribute.equals(authority.getAuthority())) {
            	return true;
            }
        }
		return false;
	}

    private String getAttribute(HttpServletRequest request){
    	String urlMap = urlMaps.get(request.getServletPath());
    	if(urlMap==null){
    		for(RequestMatcher matcher : urlMatchers.keySet()){
                if(matcher.matches(request)) return urlMatchers.get(matcher);
    		}
    	}
    	return urlMap;
    }

    public void loadUrls() {
    	authorityMaps = Maps.newLinkedHashMap();
    	urlMaps = Maps.newLinkedHashMap();
    	urlMatchers = Maps.newLinkedHashMap();
    	for(Authority authority: authorityRepository.findByNameStartsWith(prefix)){
    		if(authority.getUrl()==null || authority.equals("")) continue;
    		String[] urls = authority.getUrl().split(";");
    		authorityMaps.put(authority.getName(), urls[0]);
			for(String url: urls){
	    		if(url.contains("*")){
	        		urlMatchers.put(new AntPathRequestMatcher(url), authority.getName());
	        	}
	        	else{
	        		urlMaps.put(url, authority.getName());
	        	}
	    	}
    	}
    	logger.debug("authorityMaps: "+authorityMaps);
    	logger.debug("urlMaps: "+urlMaps);
    	logger.debug("urlMatchers: "+urlMatchers);
    }

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
