package au.com.regimo.web.social;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInAttempt;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import au.com.regimo.core.utils.SecurityUtils;

@Configuration
public class SocialConfig {

	@Value("${facebook.clientId}")
	private String facebookClientId;

	@Value("${facebook.clientSecret}")
	private String facebookClientSecret;

	@Inject
	private DataSource dataSource;
	
	@Inject
	private Environment environment;
	
	/**
	 * The locator for SaaS provider connection factories.
	 * When support for a new provider is added to Greenhouse, simply register the corresponding {@link ConnectionFactory} here.
	 * The current Environment is used to lookup the credentials assigned to the Greenhouse application by each provider during application registration.
	 * This bean is defined as a scoped-proxy so it can be serialized in support of {@link ProviderSignInAttempt provier sign-in attempts}.
	 */
	@Bean
	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)	
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(facebookClientId, facebookClientSecret));
		return registry;
	}
	
	/**
	 * The shared store for users' connection information.
	 * Uses a RDBMS-based store accessed with Spring's JdbcTemplate.
	 * The returned repository encrypts the data using the configured {@link TextEncryptor}.
	 */
	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), Encryptors.noOpText());
	}

	/**
	 * A request-scoped bean that provides the data access interface to the current user's connections.
	 * Since it is a scoped-proxy, references to this bean MAY be injected at application startup time.
	 * If no user is authenticated when the target is resolved, an {@link IllegalStateException} is thrown.
	 * @throws IllegalStateException when no user is authenticated.
	 */
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public ConnectionRepository connectionRepository() {
		Long userId = SecurityUtils.getCurrentUserId();;
		if (userId != null) {
			return usersConnectionRepository().createConnectionRepository(userId.toString());
		}
		//throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
		return null;
	}

	/**
	 * A request-scoped bean representing the API binding to Facebook for the current user.
	 * Since it is a scoped-proxy, references to this bean MAY be injected at application startup time.
	 * The target is an authorized {@link Facebook} instance if the current user has connected his or her account with a Facebook account.
	 * Otherwise, the target is a new FacebookTemplate that can invoke operations that do not require authorization.
	 */
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public Facebook facebook() {
		Connection<Facebook> facebook = connectionRepository().findPrimaryConnection(Facebook.class);
		return facebook != null ? facebook.getApi() : new FacebookTemplate();
	}

}

