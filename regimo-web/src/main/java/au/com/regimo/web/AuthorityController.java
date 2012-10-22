package au.com.regimo.web;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.regimo.core.domain.Authority;
import au.com.regimo.core.form.DataTablesColumnDef;
import au.com.regimo.core.form.DataTablesSearchCriteria;
import au.com.regimo.core.form.DataTablesSearchResult;
import au.com.regimo.core.service.AuthorityService;
import au.com.regimo.core.service.SecurityService;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/authority")
public class AuthorityController {

	private AuthorityService service;

	private SecurityService securityService;

	private final static DataTablesSearchCriteria datatable = new DataTablesSearchCriteria(
			"name,url", "standardUpdate", Lists.newArrayList(
			new DataTablesColumnDef("authority.name","45%"),
			new DataTablesColumnDef("authority.url","50%"),
			new DataTablesColumnDef("label.action","5%")));

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

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public void create(ModelMap modelMap) {
		service.loadModel(modelMap);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(ModelMap modelMap,
			@Valid Authority entity, BindingResult result) {
		if(service.saveModel(modelMap, entity, result)){
			securityService.loadUrls();
			return "redirect:edit?id="+entity.getId();
		}
		return null;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public void update(ModelMap modelMap, @RequestParam Long id) {
		service.loadModel(modelMap, id);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void update(ModelMap modelMap,
			@Valid Authority entity, BindingResult result) {
		if(service.saveModel(modelMap, entity, result)){
			securityService.loadUrls();
		}
	}

	@Inject
	public void setService(AuthorityService service) {
		this.service = service;
	}

	@Inject
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

}
