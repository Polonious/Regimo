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

import au.com.regimo.core.domain.Dashlet;
import au.com.regimo.core.form.DataTablesSearchCriteria;
import au.com.regimo.core.service.DashletService;

@Controller
@RequestMapping("/dashlet")
public class DashletController {

	private DashletService service;

	private final String modelName = "entity";

	@RequestMapping(method=RequestMethod.GET)
	public String redirect() {
		return "redirect:/dashlet/browse";
	}

	@RequestMapping(value="/browse", method=RequestMethod.GET)
	public void browse() {
	}

	@RequestMapping(value = "/browse", method=RequestMethod.POST)
	public void search(@ModelAttribute DataTablesSearchCriteria searchCriteria, ModelMap modelMap){
		service.searchFullText(searchCriteria, modelMap);
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void show(@RequestParam Long id, ModelMap modelMap) {
		modelMap.addAttribute(modelName, service.findOne(id));
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public void create(ModelMap modelMap) {
		modelMap.addAttribute(modelName, service.getNewEntity());
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(@Valid Dashlet entity, BindingResult result) {
		return update(entity, result);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public void update(@RequestParam Long id, ModelMap modelMap) {
		modelMap.addAttribute(modelName, service.findOne(id));
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid Dashlet entity, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		service.save(entity);
		return "redirect:view?id="+entity.getId();
	}

	@Inject
	public void setService(DashletService service) {
		this.service = service;
	}

}
