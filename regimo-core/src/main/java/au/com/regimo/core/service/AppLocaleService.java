package au.com.regimo.core.service;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.collect.Maps;

import au.com.regimo.core.domain.AppLocale;
import au.com.regimo.core.repository.AppLocaleRepository;

@Named
public class AppLocaleService extends GenericService<AppLocaleRepository, AppLocale> {

	@Inject
	public AppLocaleService(AppLocaleRepository repository) {
		super(repository);
	}
	
	public Map<String, String> getLocalizedMessages(String filename){
		String language = "", country = "", appCode = "";
		String[] names = filename.toLowerCase().split("_");
		for(int i=0; i<names.length; i++){
			if(i==0) language = names[i];
			else if(i==1) country = names[i];
			else if(i==2) appCode = names[i];
		}
		AppLocale appLocale = repository.findByLanguageIgnoringCaseAndCountryIgnoringCaseAndVariantIgnoringCase(
				language, country, appCode);
		
		if(!appCode.equals("")){
			String defaultFilename = "en_AU";  // TODO: load it from app.setting['locale.default'] where app.code=appCode
			String[] defaultLocale = defaultFilename.toLowerCase().split("_");
			if(!language.equals(defaultLocale[0]) || !country.equals(defaultLocale[1])){
				AppLocale defaultAppLocale = repository.findByLanguageIgnoringCaseAndCountryIgnoringCaseAndVariantIgnoringCase(
						defaultLocale[0], defaultLocale[1], appCode);
				if(defaultAppLocale!=null){
					if(appLocale!=null){
						Map<String, String> messages = Maps.newHashMap(defaultAppLocale.getMessages());
						messages.putAll(appLocale.getMessages());
						return messages;
					}
					return defaultAppLocale.getMessages();
				}
			}
		}
		
		if(appLocale!=null){
			return appLocale.getMessages();
		}
		return null;
	}

}
