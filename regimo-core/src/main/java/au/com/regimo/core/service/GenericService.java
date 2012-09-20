package au.com.regimo.core.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import au.com.regimo.core.form.DataTablesSearchCriteria;
import au.com.regimo.core.form.TransformRequired;
import au.com.regimo.core.repository.GenericRepository;
import au.com.regimo.core.utils.ReflectionUtils;

import com.google.common.base.CaseFormat;

@Transactional(readOnly = true)
public abstract class GenericService<T, ID extends Serializable> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected final Class<T> entityClass;

	protected final String entityName;

	protected abstract GenericRepository<T, ID> getRepository();

	public GenericService() {
		super();
        entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
        entityName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL,
        		entityClass.getSimpleName());
    }

	public Iterable<T> findAll(Sort sort){
		return getRepository().findAll(sort);
	}

	public Page<T> findAll(Pageable pageable){
		return getRepository().findAll(pageable);
	}

	public T findOne(Specification<T> spec){
		return getRepository().findOne(spec);
	}

	public List<T> findAll(Specification<T> spec){
		return getRepository().findAll(spec);
	}

	public Page<T> findAll(Specification<T> spec, Pageable pageable){
		return getRepository().findAll(spec, pageable);
	}

	public List<T> findAll(Specification<T> spec, Sort sort){
		return getRepository().findAll(spec, sort);
	}

	public Page<T> searchFullText(DataTablesSearchCriteria searchCriteria){
		return getRepository().findAll(fullTextSearchSpec(
				searchCriteria.getSearchableFields(), searchCriteria.getsSearch()), searchCriteria);
	}

	public Page<T> searchFullText(DataTablesSearchCriteria searchCriteria, ModelMap modelMap){
		return searchFullText(searchCriteria, modelMap, null);
	}

	public Page<T> searchFullText(DataTablesSearchCriteria searchCriteria, ModelMap modelMap,
			TransformRequired<T> transformer){
		Page<T> results = searchFullText(searchCriteria);
		modelMap.addAttribute("aaData",
				transformer==null ? results.getContent() :
					transformer.getMappedSearchResult(results.getContent()));
		modelMap.addAttribute("sEcho", searchCriteria.getsEcho());
		modelMap.addAttribute("iTotalRecords", results.getTotalElements());
		modelMap.addAttribute("iTotalDisplayRecords", results.getTotalElements());
		return results;
	}

	public long count(Specification<T> spec){
		return getRepository().count(spec);
	}

	public T getNewEntity() {
		T object = null;
        try {
        	object = entityClass.newInstance();
		} catch (Exception e) {
			logger.error("Error while create object of class "+entityClass.getSimpleName()+", exception:"+e);
		}
		return object;
    }

	@Transactional
	public T save(T entity){
		return getRepository().save(entity);
	}

	public T findOne(ID id) {
		return getRepository().findOne(id);
	}

	public void loadNewEntity(ModelMap modelMap){
		modelMap.addAttribute(getEntityName(), getNewEntity());
	}

	public void loadOne(ID id, ModelMap modelMap) {
		modelMap.addAttribute(getEntityName(), findOne(id));
	}

	public boolean exists(ID id){
		return getRepository().exists(id);
	}

	public Iterable<T> findAll(){
		return getRepository().findAll();
	}

	public long count(){
		return getRepository().count();
	}

	@Transactional
	public void delete(ID id){
		getRepository().delete(id);
	}

	@Transactional
	public void delete(T entity){
		getRepository().delete(entity);
	}

	protected Specification<T> fullTextSearchSpec(final List<String> seachableFields, final String value) {
		return new Specification<T>() {
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				Predicate predicate = cb.disjunction();
				for(String field : seachableFields){
					if(field.equals("id")){
						continue;
					}
					predicate = cb.or(predicate, cb.like(cb.upper(
							getQueryExpression(root,field).as(String.class)),
							"%"+value.toUpperCase()+"%"));
				}
				return predicate;
			}
		};
	}

	private Expression<?> getQueryExpression(Root<?> root, String pathString){
		Expression<?> expression = null;
		String[] pathElements = pathString.split("\\.");
		int pathSize = pathElements.length;

		if (pathSize > 1) {
			Join<Object, Object> path = root.join(pathElements[0]);
			for (int i = 1; i < pathSize - 1; i++) {
				path = path.join(pathElements[i]);
			}
			expression = path.get(pathElements[pathSize - 1]);
		} else {
			expression = root.get(pathString);
		}
		return expression;
	}

	public String getEntityName() {
		return entityName;
	}

}
