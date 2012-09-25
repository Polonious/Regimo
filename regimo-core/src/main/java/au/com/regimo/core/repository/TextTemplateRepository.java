package au.com.regimo.core.repository;

import au.com.regimo.core.domain.TextTemplate;

public interface TextTemplateRepository extends GenericRepository<TextTemplate>{

	TextTemplate findByName(String name);


}

