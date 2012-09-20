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

import au.com.regimo.core.domain.TextTemplate;
import au.com.regimo.core.form.DataTablesSearchCriteria;
import au.com.regimo.core.service.TextTemplateService;

@Controller
@RequestMapping("/textTemplate")
public class TextTemplateController {

	private TextTemplateService service;

	@RequestMapping(method=RequestMethod.GET)
	public String redirect() {
		return "redirect:/textTemplate/browse";
	}

	@RequestMapping(value="/browse", method=RequestMethod.GET)
	public void browse() {
	}

	@RequestMapping(value = "/browse", method=RequestMethod.POST)
	public void search(@ModelAttribute DataTablesSearchCriteria searchCriteria, ModelMap modelMap){
		service.searchFullText(searchCriteria, modelMap);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public void create(ModelMap modelMap) {
		service.loadNewEntity(modelMap);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(@Valid TextTemplate entity, BindingResult result) {
		return update(entity, result);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public void update(@RequestParam Long id, ModelMap modelMap) {
		service.loadOne(id, modelMap);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid TextTemplate entity, BindingResult result) {
		if (result.hasErrors()){
			return null;
		}
		service.save(entity);
		return "redirect:edit?id="+entity.getId();
	}

	@Inject
	public void setService(TextTemplateService service) {
		this.service = service;
	}

}
