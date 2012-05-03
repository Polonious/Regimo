package au.com.regimo.core.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.transaction.annotation.Transactional;

import au.com.regimo.core.domain.IRowStatusAllowed;
import au.com.regimo.core.domain.RowStatus;
import au.com.regimo.core.form.ComboItem;
import au.com.regimo.core.repository.RowStatusRepository;

import com.google.common.collect.Sets;

@Named
@Transactional(readOnly=true)
public class RowStatusService {

	private RowStatusRepository rowStatusRepository;
	private EntityManager entityManager;

	public void enable(IRowStatusAllowed entity) {
		setReferenceRowStatus(entity, RowStatus.CURRENT);
	}
	
	public void disable(IRowStatusAllowed entity){
		setReferenceRowStatus(entity, RowStatus.DELETED);
	}
	
	public void setReferenceRowStatus(IRowStatusAllowed entity, String reference){
		entity.setRowStatus(rowStatusRepository.findByReferenceAndStatusObject(reference, 
				entity.getClass().getSimpleName()));
	}
	
	public Collection<ComboItem> getAllowedEntityOption(){
		Collection<ComboItem> entityOption = Sets.newTreeSet(new ComboItem());
    	for(EntityType<?> entity : entityManager.getMetamodel().getEntities()){
    		if(ArrayUtils.contains(entity.getJavaType().getInterfaces(), 
    				IRowStatusAllowed.class)){
    			entityOption.add(new ComboItem(entity.getJavaType().getSimpleName()));
    		}
    	}
    	return entityOption;
	}
	
	public Collection<ComboItem> getOptions(String statusObject){
		Collection<ComboItem> options = Sets.newTreeSet(new ComboItem());
		for(RowStatus rowStatus : rowStatusRepository.findByStatusObject(statusObject)){
			options.add(new ComboItem(rowStatus.getName(), rowStatus.getId()));
		}
		return options;
	}
	
	@Inject
	public void setRowStatusRepository(RowStatusRepository rowStatusRepository) {
		this.rowStatusRepository = rowStatusRepository;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
