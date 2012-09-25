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

import com.google.common.collect.Lists;

import au.com.regimo.core.domain.TextTemplate;
import au.com.regimo.core.form.DataTablesColumnDef;
import au.com.regimo.core.form.DataTablesSearchCriteria;
import au.com.regimo.core.service.TextTemplateService;

@Controller
@RequestMapping("/textTemplate")
public class TextTemplateController {

	private TextTemplateService service;
	
	private final static DataTablesSearchCriteria datatable = new DataTablesSearchCriteria(
				"name,category,model", "standardUpdate", Lists.newArrayList(
				new DataTablesColumnDef("textTemplate.name","40%"), 
				new DataTablesColumnDef("textTemplate.category","40%"), 
				new DataTablesColumnDef("textTemplate.model","15%"), 
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
			@Valid TextTemplate entity, BindingResult result) {
		return service.saveModel(modelMap, entity, result) ? 
				"redirect:edit?id="+entity.getId() : null;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public void update(ModelMap modelMap, @RequestParam Long id) {
		service.loadModel(modelMap, id);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void update(ModelMap modelMap,
			@Valid TextTemplate entity, BindingResult result) {
		service.saveModel(modelMap, entity, result);
	}

	@Inject
	public void setService(TextTemplateService service) {
		this.service = service;
	}

}
