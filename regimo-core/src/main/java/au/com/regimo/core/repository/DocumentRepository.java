package au.com.regimo.core.repository;

import org.springframework.data.repository.CrudRepository;

import au.com.regimo.core.domain.Document;

public interface DocumentRepository extends CrudRepository<Document, Long> {

}
