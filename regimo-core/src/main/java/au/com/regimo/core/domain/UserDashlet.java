package au.com.regimo.core.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_user_dashlet")
public class UserDashlet extends IdEntity {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="dashboardId")
	private Dashboard dashboard;
	
	@ManyToOne
	@JoinColumn(name="dashletId")
	private Dashlet dashlet;

	private Integer columnIndex;
	
	private Integer displaySequence;
	
	private Integer heigth;

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public Dashlet getDashlet() {
		return dashlet;
	}

	public void setDashlet(Dashlet dashlet) {
		this.dashlet = dashlet;
	}

	public Integer getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(Integer columnIndex) {
		this.columnIndex = columnIndex;
	}

	public Integer getDisplaySequence() {
		return displaySequence;
	}

	public void setDisplaySequence(Integer displaySequence) {
		this.displaySequence = displaySequence;
	}

	public Integer getHeigth() {
		return heigth;
	}

	public void setHeigth(Integer heigth) {
		this.heigth = heigth;
	}
	
}
