package au.com.regimo.server.utils;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.EntityType;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.InitializingBean;

import au.com.regimo.core.domain.Authority;
import au.com.regimo.core.domain.IRowStatusAllowed;
import au.com.regimo.core.domain.Role;
import au.com.regimo.core.domain.RowStatus;

@Named
public class InitializeData implements InitializingBean {

	@PersistenceUnit private EntityManagerFactory emf;
	private EntityManager entityManager;

	public void afterPropertiesSet() throws Exception {

		entityManager = emf.createEntityManager();

		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();

		TypedQuery<Role> roleQuery = entityManager.createQuery(
				"from Role where name = :name", Role.class);

		Role adminRole = createRole(roleQuery, new Role("ADMIN", "Admin"));
		Role userRole  = createRole(roleQuery, new Role("USER", "User"));

		TypedQuery<Long> query = entityManager.createQuery(
				"select count(*) from RowStatus where statusObject = :statusObject and current = :current", Long.class);

		for(EntityType<?> entity : entityManager.getMetamodel().getEntities()){
    		if(ArrayUtils.contains(entity.getJavaType().getInterfaces(),
    				IRowStatusAllowed.class)){
    			if(query.setParameter("statusObject", entity.getJavaType().getSimpleName())
    					.setParameter("current", Boolean.TRUE).getSingleResult()==0){
    				createRowStatus(entity.getJavaType().getSimpleName(), Boolean.TRUE);
    			}
    			if(query.setParameter("statusObject", entity.getJavaType().getSimpleName())
    					.setParameter("current", Boolean.FALSE).getSingleResult()==0){
    				createRowStatus(entity.getJavaType().getSimpleName(), Boolean.FALSE);
    			}
    		}
    	}

		entityManager.createQuery("Update User set rowStatus =:rs where rowStatus is null").setParameter("rs",
				entityManager.createQuery("from RowStatus where reference = 'CURRENT' and statusObject = 'User'",
				RowStatus.class).getSingleResult()).executeUpdate();

		query = entityManager.createQuery(
				"select count(*) from Authority where name = :name", Long.class);

		ensureAuthority(query, new Authority("UI_MENU_ADMIN"), adminRole);
		ensureAuthority(query, new Authority("URL_ADMIN_ENDPOINTS", "/admin/endpoints"), adminRole);
		ensureAuthority(query, new Authority("URL_ADMIN_DOCUMENTS", "/admin/documents"), adminRole);

		ensureStandardAuthority(query, "user", adminRole);
		ensureAuthority(query, new Authority("URL_USER_VIEW", "/user/view"), adminRole);

		ensureStandardAuthority(query, "appLocale", adminRole);
		ensureAuthority(query, new Authority("URL_APPLOCALE_IMPORT", "/appLocale/import"), adminRole);
		ensureAuthority(query, new Authority("URL_APPLOCALE_EXPORT", "/appLocale/export"), adminRole);

		ensureStandardAuthority(query, "dashlet", adminRole);
		ensureAuthority(query, new Authority("URL_DASHLET_VIEW", "/dashlet/view"), adminRole);

		ensureStandardAuthority(query, "dashboard", adminRole);
		ensureStandardAuthority(query, "authority", adminRole);
		ensureStandardAuthority(query, "role", adminRole);
		ensureStandardAuthority(query, "rowStatus", adminRole);
		ensureStandardAuthority(query, "textTemplate", adminRole);
		ensureStandardAuthority(query, "category", adminRole);
		ensureStandardAuthority(query, "article", adminRole);

		ensureAuthority(query, new Authority("URL_PROFILE_VIEW", "/profile"), adminRole, userRole);
		ensureAuthority(query, new Authority("URL_PROFILE_EDIT", "/profile/edit"), adminRole, userRole);

		tx.commit();

		entityManager.close();

		entityManager=null;
	}

	private Role createRole(TypedQuery<Role> query, Role role){
		List<Role> roles = query.setParameter("name", role.getName()).getResultList();
		if(roles.size()>0){
			return roles.get(0);
		}
		else{
			entityManager.persist(role);
			return role;
		}
	}

	private void createRowStatus(String statusObject, Boolean current){
		RowStatus rs = new RowStatus();
		rs.setName(current?"CURRENT":"NON-CURRENT");
		rs.setDescription(rs.getName());
		rs.setReference(current?"CURRENT":"DELETED");
		rs.setStatusObject(statusObject);
		rs.setCurrent(current);
		entityManager.persist(rs);
	}

	private void ensureStandardAuthority(TypedQuery<Long> query, String name, Role... roles){
		String namePrefix = "URL_"+name.toUpperCase()+"_", urlPrefix = "/"+name;
		ensureAuthority(query, new Authority(namePrefix+"BROWSE", urlPrefix), roles);
		ensureAuthority(query, new Authority(namePrefix+"EDIT", urlPrefix+"/edit"), roles);
		ensureAuthority(query, new Authority(namePrefix+"CREATE", urlPrefix+"/new"), roles);
	}

	private void ensureAuthority(TypedQuery<Long> query, Authority authority, Role... roles){
		if(query.setParameter("name", authority.getName()).getSingleResult()==0){
			for(Role role : roles){
				authority.addRole(role);
			}
			entityManager.persist(authority);
		}
	}

}
