package au.com.regimo.core.repository;

import au.com.regimo.core.domain.AppLocale;

public interface AppLocaleRepository extends GenericRepository<AppLocale> {

	AppLocale findByLanguageIgnoringCaseAndCountryIgnoringCaseAndVariantIgnoringCase(
			String language, String country, String variant);

}
	
