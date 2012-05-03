package au.com.regimo.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_row_status")
public class RowStatus extends IdEntity {

	private static final long serialVersionUID = 1L;
	
	public static final String CURRENT = "CURRENT";
	public static final String DELETED = "DELETED";

	@Column(length = 64) 
	private String name;

	@Column(length = 255) 
	private String description;

	@Column(length = 64) 
	private String reference;

	@Column(length = 255, nullable = false) 
	private Boolean current = true;

	@Column(length = 255) 
	private String statusObject;

	public RowStatus() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Boolean getCurrent() {
		return current;
	}

	public void setCurrent(Boolean current) {
		this.current = current;
	}

	public String getStatusObject() {
		return statusObject;
	}

	public void setStatusObject(String statusObject) {
		this.statusObject = statusObject;
	}
	
}
