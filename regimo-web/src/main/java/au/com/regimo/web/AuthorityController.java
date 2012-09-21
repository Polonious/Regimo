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
import au.com.regimo.core.form.DataTablesSearchCriteria;
import au.com.regimo.core.service.AuthorityService;

@Controller
@RequestMapping("/authority")
public class AuthorityController {

	private AuthorityService service;

	@RequestMapping(method=RequestMethod.GET)
	public String browse(ModelMap modelMap) {
		return service.loadModelName(modelMap);
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
