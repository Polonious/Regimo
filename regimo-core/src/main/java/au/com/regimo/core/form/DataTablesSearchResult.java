package au.com.regimo.core.form;

import java.util.Collection;

import org.springframework.data.domain.Page;

public class DataTablesSearchResult<T> {

	private Collection<T> aaData;

	private int sEcho;

	private long iTotalRecords;

	private long iTotalDisplayRecords;

	public DataTablesSearchResult() {
		super();
	}

	public DataTablesSearchResult(int sEcho, Page<T> page) {
		this(sEcho, page.getContent(), page.getTotalElements());
	}

	public DataTablesSearchResult(int sEcho, Collection<T> aaData, long total) {
		super();
		this.aaData = aaData;
		this.sEcho = sEcho;
		this.iTotalRecords = total;
		this.iTotalDisplayRecords = total;
	}

	public Collection<T> getAaData() {
		return aaData;
	}

	public void setAaData(Collection<T> aaData) {
		this.aaData = aaData;
	}

	public int getsEcho() {
		return sEcho;
	}

	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}

	public long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

}
