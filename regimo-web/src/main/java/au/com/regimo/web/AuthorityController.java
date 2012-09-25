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

import au.com.regimo.core.domain.Authority;
import au.com.regimo.core.form.DataTablesColumnDef;
import au.com.regimo.core.form.DataTablesSearchCriteria;
import au.com.regimo.core.service.AuthorityService;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/authority")
public class AuthorityController {

	private AuthorityService service;
	
	private final static DataTablesSearchCriteria datatable = new DataTablesSearchCriteria(
			"name,url", "standardUpdate", Lists.newArrayList(
			new DataTablesColumnDef("authority.name","45%"), 
			new DataTablesColumnDef("authority.url","50%"), 
			new DataTablesColumnDef("label.action","5%")));

	@RequestMapping(method=RequestMethod.GET)
	public void browse(ModelMap modelMap) {
		modelMap.addAttribute("datatable", datatable);
	}

	@RequestMapping(method=RequestMethod.POST)
	public void browse(ModelMap modelMap,
			@ModelAttribute DataTablesSearchCriteria searchCriteria){
		service.searchFullText(searchCriteria, modelMap);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public void create(ModelMap modelMap) {
		service.loadModel(modelMap);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(ModelMap modelMap,
			@Valid Authority entity, BindingResult result) {
		return service.saveModel(modelMap, entity, result) ? 
				"redirect:edit?id="+entity.getId() : null;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public void update(ModelMap modelMap, @RequestParam Long id) {
		service.loadModel(modelMap, id);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void update(ModelMap modelMap,
			@Valid Authority entity, BindingResult result) {
		service.saveModel(modelMap, entity, result);
	}

	@Inject
	public void setService(AuthorityService service) {
		this.service = service;
	}

}
