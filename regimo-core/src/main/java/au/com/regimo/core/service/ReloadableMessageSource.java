package au.com.regimo.core.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.AbstractMessageSource;

@Named("messageSource")
public class ReloadableMessageSource extends AbstractMessageSource {

	private AppLocaleService appLocaleService;

	/** Cache to hold filename lists per Locale */
	private final Map<Locale, List<String>> cachedFilenames = new HashMap<Locale, List<String>>();

	/** Cache to hold already loaded properties per filename */
	private final Map<String, PropertiesHolder> cachedProperties = new HashMap<String, PropertiesHolder>();

	/** Cache to hold merged loaded properties per locale */
	private final Map<Locale, PropertiesHolder> cachedMergedProperties = new HashMap<Locale, PropertiesHolder>();

	/**
	 * Resolves the given message code as key in the retrieved bundle files,
	 * returning the value found in the bundle as-is (without MessageFormat parsing).
	 */
	@Override
	protected String resolveCodeWithoutArguments(String code, Locale locale) {
		PropertiesHolder propHolder = getMergedProperties(locale);
		String result = propHolder.getProperty(code);
		logger.debug(String.format("resolveCodeWithoutArguments: %s , locale:  %s , result: %s", code, locale, result));
		if (result != null) {
			return result;
		}
		return null;
	}

	/**
	 * Resolves the given message code as key in the retrieved bundle files,
	 * using a cached MessageFormat instance per message code.
	 */
	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		PropertiesHolder propHolder = getMergedProperties(locale);
		MessageFormat result = propHolder.getMessageFormat(code, locale);
		logger.debug(String.format("resolveCode: %s , locale: %s , result: %s", code, locale, result));
		if (result != null) {
			return result;
		}
		return null;
	}

	/**
	 * Get a PropertiesHolder that contains the actually visible properties
	 * for a Locale, after merging all specified resource bundles.
	 * Either fetches the holder from the cache or freshly loads it.
	 */
	protected PropertiesHolder getMergedProperties(Locale locale) {
		synchronized (this.cachedMergedProperties) {
			PropertiesHolder mergedHolder = this.cachedMergedProperties.get(locale);
			if (mergedHolder != null) {
				return mergedHolder;
			}
			Properties mergedProps = new Properties();
			mergedHolder = new PropertiesHolder(mergedProps);
			List<String> filenames = calculateAllFilenames(locale);
			for (int j = filenames.size() - 1; j >= 0; j--) {
				String filename = filenames.get(j);
				PropertiesHolder propHolder = getProperties(filename);
				if (propHolder.getProperties() != null) {
					mergedProps.putAll(propHolder.getProperties());
				}
			}
			this.cachedMergedProperties.put(locale, mergedHolder);
			return mergedHolder;
		}
	}

	/**
	 * Calculate all filenames for the given bundle Locale.
	 * Will calculate filenames for the given Locale, the system Locale
	 * (if applicable), and the default file.
	 * @param locale the locale
	 * @return the List of filenames to check
	 * @see #calculateFilenamesForLocale
	 */
	protected List<String> calculateAllFilenames(Locale locale) {
		synchronized (this.cachedFilenames) {
			List<String> filenames = this.cachedFilenames.get(locale);
			if (filenames != null) {
				return filenames;
			}
			filenames = new ArrayList<String>(7);
			filenames.addAll(calculateFilenamesForLocale(locale));
			cachedFilenames.put(locale, filenames);
			logger.debug(String.format("cachedFilenames: %s", cachedFilenames));
			return filenames;
		}
	}

	/**
	 * Calculate the filenames for the given bundle Locale,
	 * appending language code, country code, and variant code.
	 * E.g.: Locale "de_AT_oo" -> "de_AT_OO", "de_AT", "de".
	 * <p>Follows the rules defined by {@link java.util.Locale#toString()}.
	 * @param locale the locale
	 * @return the List of filenames to check
	 */
	protected List<String> calculateFilenamesForLocale(Locale locale) {
		List<String> result = new ArrayList<String>(3);
		String language = locale.getLanguage();
		String country = locale.getCountry();
		String variant = locale.getVariant();
		StringBuilder temp = new StringBuilder();

		if (language.length() > 0) {
			temp.append(language);
			result.add(0, temp.toString());
		}

		temp.append('_');
		if (country.length() > 0) {
			temp.append(country);
			result.add(0, temp.toString());
		}

		if (variant.length() > 0 && (language.length() > 0 || country.length() > 0)) {
			temp.append('_').append(variant);
			result.add(0, temp.toString());
		}

		return result;
	}

	/**
	 * Get a PropertiesHolder for the given filename, either from the
	 * cache or freshly loaded.
	 * @param filename the bundle filename (Locale)
	 * @return the current PropertiesHolder for the bundle
	 */
	protected PropertiesHolder getProperties(String filename) {
		synchronized (this.cachedProperties) {
			PropertiesHolder propHolder = this.cachedProperties.get(filename);
			if (propHolder != null) {
				// up to date
				return propHolder;
			}
			return refreshProperties(filename, propHolder);
		}
	}

	/**
	 * Refresh the PropertiesHolder for the given bundle filename.
	 * The holder can be <code>null</code> if not cached before.
	 * @param filename the bundle filename (Locale)
	 * @param propHolder the current PropertiesHolder for the bundle
	 */
	protected PropertiesHolder refreshProperties(String filename, PropertiesHolder propHolder) {
		logger.debug(String.format("refreshProperties: %s", filename));
		Map<String, String> messages = appLocaleService.getLocalizedMessages(filename);

		if (messages!=null && messages.size()>0) {
			Properties props = new Properties();
			props.putAll(messages);
			logger.debug(String.format("props messages size: ", messages.size()));
			propHolder = new PropertiesHolder(props);
		}
		else {
			// Resource does not exist.
			if (logger.isDebugEnabled()) {
				logger.debug("No properties found for [" + filename + "]");
			}
			// Empty holder representing "not found".
			propHolder = new PropertiesHolder();
		}

		this.cachedProperties.put(filename, propHolder);
		return propHolder;
	}

	/**
	 * Clear the resource bundle cache.
	 * Subsequent resolve calls will lead to reloading of the properties files.
	 */
	public void clearCache() {
		logger.debug("Clearing entire resource bundle cache");
		synchronized (this.cachedProperties) {
			this.cachedProperties.clear();
		}
		synchronized (this.cachedMergedProperties) {
			this.cachedMergedProperties.clear();
		}
	}

	/**
	 * Clear the resource bundle caches of this MessageSource and all its ancestors.
	 * @see #clearCache
	 */
	public void clearCacheIncludingAncestors() {
		clearCache();
		if (getParentMessageSource() instanceof ReloadableMessageSource) {
			((ReloadableMessageSource) getParentMessageSource()).clearCacheIncludingAncestors();
		}
	}

	@Override
	public String toString() {
		return getClass().getName() + ": cachedFilenames=[" + this.cachedFilenames + "]";
	}

	/**
	 * PropertiesHolder for caching.
	 * Stores the last-modified timestamp of the source file for efficient
	 * change detection, and the timestamp of the last refresh attempt
	 * (updated every time the cache entry gets re-validated).
	 */
	protected class PropertiesHolder {

		private Properties properties;

		/** Cache to hold already generated MessageFormats per message code */
		private final Map<String, Map<Locale, MessageFormat>> cachedMessageFormats =
				new HashMap<String, Map<Locale, MessageFormat>>();

		public PropertiesHolder(Properties properties) {
			this.properties = properties;
		}

		public PropertiesHolder() {
		}

		public Properties getProperties() {
			return properties;
		}

		public String getProperty(String code) {
			if (this.properties == null) {
				return null;
			}
			return this.properties.getProperty(code);
		}

		public MessageFormat getMessageFormat(String code, Locale locale) {
			if (this.properties == null) {
				return null;
			}
			synchronized (this.cachedMessageFormats) {
				Map<Locale, MessageFormat> localeMap = this.cachedMessageFormats.get(code);
				if (localeMap != null) {
					MessageFormat result = localeMap.get(locale);
					if (result != null) {
						return result;
					}
				}
				String msg = this.properties.getProperty(code);
				if (msg != null) {
					if (localeMap == null) {
						localeMap = new HashMap<Locale, MessageFormat>();
						this.cachedMessageFormats.put(code, localeMap);
					}
					MessageFormat result = createMessageFormat(msg, locale);
					localeMap.put(locale, result);
					return result;
				}
				return null;
			}
		}
	}

	@Inject
	public void setAppLocaleService(AppLocaleService appLocaleService) {
		this.appLocaleService = appLocaleService;
		this.setUseCodeAsDefaultMessage(true);
	}

}

