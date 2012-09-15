package au.com.regimo.web;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.regimo.core.domain.Dashlet;
import au.com.regimo.core.form.DataTablesSearchCriteria;
import au.com.regimo.core.service.DashletService;

@Controller
@RequestMapping(value="/dashlet")
public class DashletController {

	private DashletService service;

	private final String modelName = "entity";

	@RequestMapping(method=RequestMethod.GET)
	public String redirect() {
		return "redirect:"+service.getEntityName()+"/browse";
	}
	
	@RequestMapping(value="/browse", method=RequestMethod.GET)
	public void browse() {
	}
	
	@RequestMapping(value = "/search")
	public void search(@ModelAttribute DataTablesSearchCriteria searchCriteria, ModelMap modelMap){
		Page<Dashlet> page = service.searchFullText(searchCriteria);
		modelMap.addAttribute("aaData", page.getContent());
		modelMap.addAttribute("sEcho", searchCriteria.getsEcho());
		modelMap.addAttribute("iTotalRecords", page.getTotalElements());
		modelMap.addAttribute("iTotalDisplayRecords", page.getTotalElements());
	}
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, ModelMap modelMap) {
		modelMap.addAttribute(modelName, service.findOne(id));
		return service.getEntityName()+"/view";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public void create(ModelMap modelMap) {
		modelMap.addAttribute(modelName, service.getNewEntity());
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(@Valid Dashlet entity, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		service.save(entity);
		return "redirect:view/"+entity.getId();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String update(@PathVariable Long id, ModelMap modelMap) {
		modelMap.addAttribute(modelName, service.findOne(id));
		return service.getEntityName()+"/edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid Dashlet entity, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		service.save(entity);
		return "redirect:/"+service.getEntityName()+"/view/"+entity.getId();
	}
	
	@Inject
	public void setService(DashletService service) {
		this.service = service;
	}
	
}
