package au.com.regimo.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_audit_log_detail", allocationSize=1)
public class AuditLogDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE , generator="SEQ_STORE") 
    private Long id;

	@ManyToOne
	@JoinColumn(name="auditLogId")
	private AuditLog auditLog;
	
	@Column
	private String fieldName;
	
	@Column(length = 8000)
	private String oldValue;
	
	@Column(length = 8000)
	private String newValue;

	public AuditLogDetail() {
	}

	public AuditLogDetail(AuditLog auditLog) {
		super();
		this.auditLog = auditLog;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuditLog getAuditLog() {
		return auditLog;
	}

	public void setAuditLog(AuditLog auditLog) {
		this.auditLog = auditLog;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	
}
