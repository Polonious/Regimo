package au.com.regimo.core.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_dashlet")
public class Dashlet extends IdEntity {

	private static final long serialVersionUID = 1L;
	
	public static final String TYPE_FREEMARKER	= "freemarker";
	public static final String TYPE_EXTRENAL	= "external";
	public static final String TYPE_UNDEFINED	= "undefined";

	private String title;
	
	private String type;
	
	@Column(length = 8000)
	private String content;
	
	private String model;
	
	private String parameter;

    @ManyToMany(targetEntity = WebDevice.class)
    @JoinTable(name = "DashletWebDevice", joinColumns = @JoinColumn(name = "dashletId"), inverseJoinColumns = @JoinColumn(name = "webDeviceId"))
    private Set<WebDevice> webDevices;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Set<WebDevice> getWebDevices() {
		return webDevices;
	}

	public void setWebDevices(Set<WebDevice> webDevices) {
		this.webDevices = webDevices;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
}
