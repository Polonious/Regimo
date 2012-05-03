package au.com.regimo.server.utils;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.EntityType;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.InitializingBean;

import au.com.regimo.core.domain.IRowStatusAllowed;
import au.com.regimo.core.domain.Role;
import au.com.regimo.core.domain.RowStatus;

@Named
public class InitializeData implements InitializingBean {

	@PersistenceUnit private EntityManagerFactory emf;
	
	public void afterPropertiesSet() throws Exception {
		
		EntityManager entityManager = emf.createEntityManager();
		
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();

		TypedQuery<Long> query = entityManager.createQuery(
				"select count(*) from Role where name = :name", Long.class);
		
		if(query.setParameter("name", "ADMIN").getSingleResult()==0){
			Role adminRole = new Role("ADMIN","Admin");
			entityManager.persist(adminRole);
		}
		
		if(query.setParameter("name", "USER").getSingleResult()==0){
			Role userRole = new Role("USER","User");
			entityManager.persist(userRole);
		}

		query = entityManager.createQuery(
				"select count(*) from RowStatus where statusObject = :statusObject and current = :current", Long.class);
		
		for(EntityType<?> entity : entityManager.getMetamodel().getEntities()){
    		if(ArrayUtils.contains(entity.getJavaType().getInterfaces(), 
    				IRowStatusAllowed.class)){
    			if(query.setParameter("statusObject", entity.getJavaType().getSimpleName())
    					.setParameter("current", Boolean.TRUE).getSingleResult()==0){
    				createRowStatus(entityManager, entity.getJavaType().getSimpleName(), Boolean.TRUE);
    			}
    			if(query.setParameter("statusObject", entity.getJavaType().getSimpleName())
    					.setParameter("current", Boolean.FALSE).getSingleResult()==0){
    				createRowStatus(entityManager, entity.getJavaType().getSimpleName(), Boolean.FALSE);
    			}
    		}
    	}
		
		entityManager.createQuery("Update User set rowStatus =:rs where rowStatus is null").setParameter("rs", 
				entityManager.createQuery("from RowStatus where reference = 'CURRENT' and statusObject = 'User'", 
				RowStatus.class).getSingleResult()).executeUpdate();

		tx.commit();

		entityManager.close();
	}
	
	private void createRowStatus(EntityManager entityManager, String statusObject, Boolean current){
		RowStatus rs = new RowStatus();
		rs.setName(current?"CURRENT":"NON-CURRENT");
		rs.setDescription(rs.getName());
		rs.setReference(current?"CURRENT":"DELETED");
		rs.setStatusObject(statusObject);
		rs.setCurrent(current);
		entityManager.persist(rs);
	}
}
