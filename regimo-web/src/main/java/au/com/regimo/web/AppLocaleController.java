package au.com.regimo.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.PropertiesPersister;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import au.com.regimo.core.domain.AppLocale;
import au.com.regimo.core.form.DataTablesColumnDef;
import au.com.regimo.core.form.DataTablesSearchCriteria;
import au.com.regimo.core.form.DataTablesSearchResult;
import au.com.regimo.core.form.XhrEvent;
import au.com.regimo.core.service.AppLocaleService;
import au.com.regimo.core.service.ReloadableMessageSource;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

@Controller
@RequestMapping("/appLocale")
public class AppLocaleController {

	private AppLocaleService service;
	private ReloadableMessageSource messageSource;

	private final static DataTablesSearchCriteria datatable = new DataTablesSearchCriteria(
			"language,country,variant", "standardUpdate", Lists.newArrayList(
			new DataTablesColumnDef("messageSource.language","20%"),
			new DataTablesColumnDef("messageSource.country","20%"),
			new DataTablesColumnDef("messageSource.variant","50%"),
			new DataTablesColumnDef("label.action","10%")));

	@RequestMapping(method=RequestMethod.GET)
	@ModelAttribute("datatable")
	public DataTablesSearchCriteria browse() {
		return datatable;
	}

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public DataTablesSearchResult<?> browse(
			@ModelAttribute DataTablesSearchCriteria searchCriteria){
		return service.searchFullText(searchCriteria);
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void view(ModelMap modelMap, @RequestParam Long id) {
		service.loadModel(modelMap, id);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public void create(ModelMap modelMap) {
		service.loadModel(modelMap);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public void create(@Valid AppLocale entity, BindingResult binding, ModelMap modelMap) {
		update(entity, binding, modelMap);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public void update(ModelMap modelMap, @RequestParam Long id) {
		service.loadModel(modelMap, id);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void update(@Valid AppLocale entity, BindingResult binding, ModelMap modelMap) {
		XhrEvent event = service.saveModel(entity, binding);
		if(event.isSuccess()){
			messageSource.clearCache();
		}
		modelMap.put("appLocale", event);
	}

	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public Properties importProperties(@RequestParam("file") MultipartFile file) throws IOException {
		Properties props = new Properties();
		PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
		InputStream is = file.getInputStream();
		propertiesPersister.load(props, new InputStreamReader(is, "UTF-8"));
		return props;
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void export(@RequestParam Long id, HttpServletResponse response) throws IOException {
		AppLocale appLocale = service.findOne(id);
		
		String locale = String.format("%s_%s_%s", 
				appLocale.getLanguage(), 
				appLocale.getCountry(), 
				appLocale.getVariant());
		String prefix = "messages_"+locale;
		File file = File.createTempFile(prefix, ".properties");
		file.deleteOnExit();
		
		Properties properties = new Properties() {
			private static final long serialVersionUID = 1L;
			@Override
			public Set<Object> keySet() {
				return Collections.unmodifiableSet(new TreeSet<Object>(super
						.keySet()));
			}
			@Override
		    public synchronized Enumeration<Object> keys() {
		        return Collections.enumeration(new TreeSet<Object>(super.keySet()));
		    }
		};
        for (Map.Entry<String, String> entry : appLocale.getMessages().entrySet()) {
            properties.setProperty(entry.getKey(), entry.getValue());
        }
        
        properties.store(new OutputStreamWriter(new FileOutputStream(file), 
        		"UTF8"), "messages for locale " + locale);
        
		response.setContentType("text/plain");
        response.setContentLength(Ints.saturatedCast(file.length()));
        response.setHeader("Content-Disposition","attachment; filename=\"" 
                        + prefix +".properties\"");
        FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
	}
	
	@Inject
	public void setService(AppLocaleService service) {
		this.service = service;
	}

	@Inject
	public void setMessageSource(ReloadableMessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
