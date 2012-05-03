package au.com.regimo.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_audit_log", allocationSize=1)
public class AuditLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE , generator="SEQ_STORE") 
    private Long id;

	@Enumerated(EnumType.STRING)
	private AuditLogOperation operation;
		
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	private String entity;
	
	private Long entityId;
	
	private Date timestamp;

	@OneToMany(mappedBy = "auditLog")
	private List<AuditLogDetail> auditLogDetails = new LinkedList<AuditLogDetail>();
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuditLogOperation getOperation() {
		return operation;
	}

	public void setOperation(AuditLogOperation operation) {
		this.operation = operation;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@JsonIgnore
	public List<AuditLogDetail> getAuditLogDetails() {
		return auditLogDetails;
	}

	public void setAuditLogDetails(List<AuditLogDetail> auditLogDetails) {
		this.auditLogDetails = auditLogDetails;
	}
}
