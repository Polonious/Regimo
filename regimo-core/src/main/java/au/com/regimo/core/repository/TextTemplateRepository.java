package au.com.regimo.core.repository;

import au.com.regimo.core.domain.TextTemplate;

public interface TextTemplateRepository extends GenericRepository<TextTemplate, Long>{

	TextTemplate findByName(String name);


}

