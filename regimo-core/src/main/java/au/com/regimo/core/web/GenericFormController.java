package au.com.regimo.core.web;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.regimo.core.domain.IdEntity;

public abstract class GenericFormController<T extends IdEntity> extends GenericEntityController<T> {

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute T entity, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		getEntityService().save(entity);
		return "redirect:view/"+entity.getId();
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute T entity, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		getEntityService().save(entity);
		return "redirect:/"+getEntityService().getEntityName()+"/view/"+entity.getId();
	}

}
