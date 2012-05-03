package au.com.regimo.core.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_dashboard")
public class Dashboard extends IdEntity {

	private static final long serialVersionUID = 1L;

	private String viewName; 
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	private Integer columnCount;
	
	private String columnWidth;
	
    @ManyToMany(targetEntity = WebDevice.class)
    @JoinTable(name = "DashboardWebDevice", joinColumns = @JoinColumn(name = "dashboardId"), inverseJoinColumns = @JoinColumn(name = "webDeviceId"))
    private Set<WebDevice> webDevices;

	@OneToMany(mappedBy = "dashboard")
	@OrderBy("columnIndex,displaySequence")
	private List<UserDashlet> userDashlets = new LinkedList<UserDashlet>();
    
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(Integer columnCount) {
		this.columnCount = columnCount;
	}

	public String getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(String columnWidth) {
		this.columnWidth = columnWidth;
	}

	public Set<WebDevice> getWebDevices() {
		return webDevices;
	}

	public void setWebDevices(Set<WebDevice> webDevices) {
		this.webDevices = webDevices;
	}

	@JsonIgnore
	public List<UserDashlet> getUserDashlets() {
		return userDashlets;
	}

	public void setUserDashlets(List<UserDashlet> userDashlets) {
		this.userDashlets = userDashlets;
	}
	
}
