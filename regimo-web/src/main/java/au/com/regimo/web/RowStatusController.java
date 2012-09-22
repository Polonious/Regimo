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

import au.com.regimo.core.domain.RowStatus;
import au.com.regimo.core.form.DataTablesSearchCriteria;
import au.com.regimo.core.service.RowStatusService;

@Controller
@RequestMapping("/rowStatus")
public class RowStatusController {

	private RowStatusService service;

	@RequestMapping(method=RequestMethod.GET)
	public void browse() {
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
			@Valid RowStatus entity, BindingResult result) {
		return service.saveModel(modelMap, entity, result) ? 
				"redirect:edit?id="+entity.getId() : null;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public void update(ModelMap modelMap, @RequestParam Long id) {
		service.loadModel(modelMap, id);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void update(ModelMap modelMap,
			@Valid RowStatus entity, BindingResult result) {
		service.saveModel(modelMap, entity, result);
	}

	@Inject
	public void setService(RowStatusService service) {
		this.service = service;
	}

}
