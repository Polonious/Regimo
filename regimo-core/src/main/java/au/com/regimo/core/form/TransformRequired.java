package au.com.regimo.core.form;

import java.util.List;

public interface TransformRequired<T> {

	List<?> getMappedSearchResult(List<T> result);
	
}
