package au.com.regimo.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import au.com.regimo.core.domain.Dashlet;
import au.com.regimo.core.service.DashletService;
import au.com.regimo.core.web.GenericFormController;

@Controller
@RequestMapping(value="/dashlet")
public class DashletController extends GenericFormController<Dashlet> {

	private DashletService entityService;

	@Inject
	public void setEntityService(DashletService entityService) {
		this.entityService = entityService;
	}

	@Override
	protected DashletService getEntityService() {
		return entityService;
	}
	
}
