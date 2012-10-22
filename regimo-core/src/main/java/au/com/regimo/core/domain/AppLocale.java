package au.com.regimo.core.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_app_locale")
public class AppLocale extends IdEntity {

	private static final long serialVersionUID = 1L;

    @Column(length = 8)
	@NotEmpty(message="{appLocale.language.notEmpty}")
	private String language;

    @Column(length = 3)
	private String country;

    @Column(length = 63)
	private String variant;
	
    @ElementCollection
    @MapKeyColumn(name="key", length=255)
    @Column(name="value", columnDefinition="text")
    @CollectionTable(name="AppLocaleMessage", joinColumns=@JoinColumn(name="AppLocaleId"))
    private Map<String, String> messages = new HashMap<String, String>();

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}
	
}
