package au.com.regimo.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import au.com.regimo.core.domain.TextTemplate;
import au.com.regimo.core.repository.TextTemplateRepository;

@Named
public class TextTemplateService extends GenericService<TextTemplateRepository, TextTemplate> {

	@Inject
	public TextTemplateService(TextTemplateRepository repository) {
		super(repository);
	}

}
