package au.com.regimo.core.form;

public class DataTablesColumnDef {
	
	private String mDataProp;

	private Boolean bSearchable;
	
	private String sSearch;
	
	private Boolean bRegex;
	
	private Boolean bSortable;

	public String getmDataProp() {
		return mDataProp;
	}

	public void setmDataProp(String mDataProp) {
		this.mDataProp = mDataProp;
	}

	public Boolean getbSearchable() {
		return bSearchable;
	}

	public void setbSearchable(Boolean bSearchable) {
		this.bSearchable = bSearchable;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public Boolean getbRegex() {
		return bRegex;
	}

	public void setbRegex(Boolean bRegex) {
		this.bRegex = bRegex;
	}

	public Boolean getbSortable() {
		return bSortable;
	}

	public void setbSortable(Boolean bSortable) {
		this.bSortable = bSortable;
	}
		
}
