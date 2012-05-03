package au.com.regimo.core.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.regimo.core.domain.IdEntity;
import au.com.regimo.core.form.DataTablesSearchCriteria;
import au.com.regimo.core.service.GenericService;

public abstract class GenericEntityController<T extends IdEntity> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	protected final String modelName = "entity";
	
	protected abstract GenericService<T, Long> getEntityService();
	
	@RequestMapping(method=RequestMethod.GET)
	public String redirect() {
		return "redirect:"+getEntityService().getEntityName()+"/browse";
	}
	
	@RequestMapping(value="/browse", method=RequestMethod.GET)
	public void browse() {
	}
	
	@RequestMapping(value = "/search")
	public void search(@ModelAttribute DataTablesSearchCriteria searchCriteria, ModelMap modelMap){
		Page<T> page = getEntityService().searchFullText(searchCriteria);
		modelMap.addAttribute("aaData", getMappedSearchResult(page.getContent()));
		modelMap.addAttribute("sEcho", searchCriteria.getsEcho());
		modelMap.addAttribute("iTotalRecords", page.getTotalElements());
		modelMap.addAttribute("iTotalDisplayRecords", page.getTotalElements());
	}
	
	protected List<?> getMappedSearchResult(List<T> result){
		return result;
	}
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, ModelMap modelMap) {
		modelMap.addAttribute(modelName, getEntityService().findOne(id));
		return getEntityService().getEntityName()+"/view";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public void create(ModelMap modelMap) {
		modelMap.addAttribute(modelName, getEntityService().getNewEntity());
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String update(@PathVariable Long id, ModelMap modelMap) {
		modelMap.addAttribute(modelName, getEntityService().findOne(id));
		return getEntityService().getEntityName()+"/edit";
	}

}
