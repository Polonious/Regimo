package au.com.regimo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.regimo.core.domain.TextTemplate;

public interface TextTemplateRepository extends JpaRepository<TextTemplate, Long>{
	
	TextTemplate findByName(String name);
	
	
}

