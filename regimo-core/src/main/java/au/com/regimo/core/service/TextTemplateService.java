package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.core.domain.TextTemplate;
import au.com.regimo.core.repository.TextTemplateRepository;

@Named
public class TextTemplateService extends GenericService<TextTemplate, Long> {

	private TextTemplateRepository repository;

	@Override
	protected TextTemplateRepository getRepository() {
		return repository;
	}

	@Inject
	public void setRepository(TextTemplateRepository repository) {
		this.repository = repository;
	}

}
