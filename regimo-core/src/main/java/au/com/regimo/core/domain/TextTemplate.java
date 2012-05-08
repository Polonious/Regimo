package au.com.regimo.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_text_template")
public class TextTemplate extends IdEntity implements IAuditable {

	private static final long serialVersionUID = 1L;
	
	public static final String CATEGORY_ACTION	= "Action";
	public static final String CATEGORY_JOB	= "Job";
	
	@Column(length = 64)
	private String name;
	
    
	@Column(length = 8000)
	private String content;
	
	@Column(length = 64)
	private String category;

	@Column(length = 255)
	private String model;
	
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}

