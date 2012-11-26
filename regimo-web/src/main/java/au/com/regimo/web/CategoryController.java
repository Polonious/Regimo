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

import au.com.regimo.cms.domain.Category;
import au.com.regimo.cms.service.CategoryService;
import au.com.regimo.core.form.DataTablesColumnDef;
import au.com.regimo.core.form.DataTablesSearchCriteria;
import au.com.regimo.core.form.DataTablesSearchResult;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/category")
public class CategoryController {

	private CategoryService service;

	private final static DataTablesSearchCriteria datatable = new DataTablesSearchCriteria(
			"name,slug", "standardUpdate", Lists.newArrayList(
					new DataTablesColumnDef("category.name","50%"),
					new DataTablesColumnDef("category.slug","45%"),
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
			@Valid Category entity, BindingResult result) {
		return service.saveModel(modelMap, entity, result) ?
				"redirect:edit?id="+entity.getId() : null;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public void update(ModelMap modelMap, @RequestParam Long id) {
		service.loadModel(modelMap, id);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void update(ModelMap modelMap,
			@Valid Category entity, BindingResult result) {
		service.saveModel(modelMap, entity, result);
	}

	@Inject
	public void setService(CategoryService service) {
		this.service = service;
	}

}
