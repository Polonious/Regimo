package au.com.regimo.core.repository;

import org.springframework.data.repository.CrudRepository;

import au.com.regimo.core.domain.AuditLog;

public interface AuditLogRepository extends CrudRepository<AuditLog, Long> {

}
