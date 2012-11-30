package au.com.regimo.core.form;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.google.common.collect.Lists;

public class DataTablesSearchCriteria implements Serializable, Pageable {

	private static final long serialVersionUID = 1L;
	
	private int sEcho;
	
	private int iColumns;
	
	private String sColumns;
	
	private int iDisplayStart;
	
	private int iDisplayLength;
	
	private String sSearch;
	
	private boolean bRegex;
	
	private List<DataTablesColumnDef> columnDefs;
	
	private int iSortingCols;
	
	private List<Integer> iSortCol_;
	
	private List<String> sSortDir_;
	
	private String action;
	
	public DataTablesSearchCriteria() {
		super();
	}

	public DataTablesSearchCriteria(String sColumns, String action, List<DataTablesColumnDef> columnDefs) {
		super();
		this.sColumns = sColumns;
		this.action = action;
		this.columnDefs = columnDefs;
	}

	public int getsEcho() {
		return sEcho;
	}

	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}

	public int getiColumns() {
		return iColumns;
	}

	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public boolean isbRegex() {
		return bRegex;
	}

	public void setbRegex(boolean bRegex) {
		this.bRegex = bRegex;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public List<Integer> getiSortCol_() {
		return iSortCol_;
	}

	public void setiSortCol_(List<Integer> iSortCol_) {
		this.iSortCol_ = iSortCol_;
	}

	public List<String> getsSortDir_() {
		return sSortDir_;
	}

	public void setsSortDir_(List<String> sSortDir_) {
		this.sSortDir_ = sSortDir_;
	}

	public List<DataTablesColumnDef> getColumnDefs() {
		return columnDefs;
	}

	public void setColumnDefs(List<DataTablesColumnDef> columnDefs) {
		this.columnDefs = columnDefs;
	}

	@JsonIgnore
	public int getPageNumber() {
		return iDisplayStart/iDisplayLength;
	}

	@JsonIgnore
	public int getPageSize() {
		return iDisplayLength;
	}

	@JsonIgnore
	public int getOffset() {
		return iDisplayStart;
	}

	@JsonIgnore
	public Sort getSort() {
		if(iSortCol_!=null && columnDefs!=null && sSortDir_!=null){
			List<Order> orders = Lists.newLinkedList();
			String[] sortDir = sSortDir_.toArray(new String[sSortDir_.size()]);
			int i=0;
			for(Integer sortCol : iSortCol_){
				if(sortCol<columnDefs.size() && i<sortDir.length){
					int j=0;
					for(DataTablesColumnDef columnDef : columnDefs){
						if(j==sortCol.intValue()){
							orders.add(new Order(Direction.fromString(sortDir[i]), columnDef.getmDataProp()));
							break;
						}
						j++;
					}
				}
				i++;
			}
			if(orders.size()>0){
				return new Sort(orders);
			}
		}
		return null;
	}

	public List<String> getSearchableFields(){
		List<String> fields = Lists.newLinkedList();
		if(columnDefs!=null){
			for(DataTablesColumnDef columnDef : columnDefs){
				if(columnDef.getbSearchable()){
					fields.add(columnDef.getmDataProp());
				}
			}
		}
		return fields;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
}
