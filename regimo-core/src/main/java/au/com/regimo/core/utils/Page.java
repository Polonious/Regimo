package au.com.regimo.core.utils;

import java.util.ArrayList;
import java.util.List;

public class Page<E> {

    private int pageNumber;
    private int pageSize;
    private int pagesAvailable;
    private int rowCount;
    private List<E> items = new ArrayList<E>();

    public Page() {
		super();
		this.pageNumber = 1;
		this.pageSize = 20;
	}

	public Page(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
    	this.pageSize = pageSize;
    }
    
	public void setPagesAvailable(int pagesAvailable) {
        this.pagesAvailable = pagesAvailable;
    }

    public void setItems(List<E> items) {
        this.items = items;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
    	return pageSize;
    }
    
    public int getPagesAvailable() {
        return pagesAvailable;
    }

    public List<E> getItems() {
        return items;
    }

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
    
}
