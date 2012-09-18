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
	
	// Map<url, Map<method, attribute>>, method='' means '*'
	private Map<String, String> urlMaps; 
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
        int result = ACCESS_ABSTAIN;

    	String attribute = getAttribute(fi.getRequest());
    	if(attribute!=null){
			result = ACCESS_DENIED;
            // Attempt to find a matching granted authority
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (attribute.equals(authority.getAuthority())) {
                	result = ACCESS_GRANTED;
                	break;
                }
            }
    	}
        logger.debug(fi+" , attribute: "+attribute+" , result: "+result);
        return result;
    }

	public boolean isAuthorised(String url){
		String attribute = getAttribute(new FilterInvocation(url, "get").getRequest());
		logger.debug("isAuthorised: "+url+" , attribute: "+attribute);
		return isPermit(attribute);
	}
	
	public boolean isPermit(String attribute){
		if(attribute!=null){
			for (GrantedAuthority authority : SecurityUtils.getAuthorities()) {
	            if (attribute.equals(authority.getAuthority())) {
	            	return true;
	            }
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
    	urlMaps = Maps.newLinkedHashMap();
    	urlMatchers = Maps.newLinkedHashMap();
    	for(Authority authority: authorityRepository.findByNameStartsWith(prefix)){
    		if(authority.getUrl()==null || authority.equals("")) continue;
			for(String url: authority.getUrl().split(",")){
	    		if(url.contains("*")){
	        		urlMatchers.put(new AntPathRequestMatcher(url), authority.getName());
	        	}
	        	else{
	        		urlMaps.put(url, authority.getName());
	        	}
	    	}
    	}
    	logger.debug("urlMaps: "+urlMaps);
    	logger.debug("urlMatchers: "+urlMatchers);
    }

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
