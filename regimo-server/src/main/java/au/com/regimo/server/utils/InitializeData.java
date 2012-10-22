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

		createAuthority(query, new Authority("UI_MENU_ADMIN"), adminRole);
		createAuthority(query, new Authority("URL_ADMIN_ENDPOINTS", "/admin/endpoints"), adminRole);

		createAuthority(query, new Authority("URL_USER_BROWSE", "/user"), adminRole);
		createAuthority(query, new Authority("URL_USER_VIEW", "/user/view"), adminRole);
		createAuthority(query, new Authority("URL_USER_EDIT", "/user/edit"), adminRole);
		createAuthority(query, new Authority("URL_USER_CREATE", "/user/new"), adminRole);

		createAuthority(query, new Authority("URL_APPLOCALE_BROWSE", "/appLocale"), adminRole);
		createAuthority(query, new Authority("URL_APPLOCALE_EDIT", "/appLocale/edit"), adminRole);
		createAuthority(query, new Authority("URL_APPLOCALE_CREATE", "/appLocale/new"), adminRole);
		createAuthority(query, new Authority("URL_APPLOCALE_IMPORT", "/appLocale/import"), adminRole);
		createAuthority(query, new Authority("URL_APPLOCALE_EXPORT", "/appLocale/export"), adminRole);

		createAuthority(query, new Authority("URL_DASHLET_BROWSE", "/dashlet"), adminRole);
		createAuthority(query, new Authority("URL_DASHLET_VIEW", "/dashlet/view"), adminRole);
		createAuthority(query, new Authority("URL_DASHLET_EDIT", "/dashlet/edit"), adminRole);
		createAuthority(query, new Authority("URL_DASHLET_CREATE", "/dashlet/new"), adminRole);

		createAuthority(query, new Authority("URL_DASHBOARD_BROWSE", "/dashboard"), adminRole);
		createAuthority(query, new Authority("URL_DASHBOARD_EDIT", "/dashboard/edit"), adminRole);
		createAuthority(query, new Authority("URL_DASHBOARD_CREATE", "/dashboard/new"), adminRole);

		createAuthority(query, new Authority("URL_AUTHORITY_BROWSE", "/authority"), adminRole);
		createAuthority(query, new Authority("URL_AUTHORITY_EDIT", "/authority/edit"), adminRole);
		createAuthority(query, new Authority("URL_AUTHORITY_CREATE", "/authority/new"), adminRole);

		createAuthority(query, new Authority("URL_ROLE_BROWSE", "/role"), adminRole);
		createAuthority(query, new Authority("URL_ROLE_EDIT", "/role/edit"), adminRole);
		createAuthority(query, new Authority("URL_ROLE_CREATE", "/role/new"), adminRole);

		createAuthority(query, new Authority("URL_ROWSTATUS_BROWSE", "/rowStatus"), adminRole);
		createAuthority(query, new Authority("URL_ROWSTATUS_EDIT", "/rowStatus/edit"), adminRole);
		createAuthority(query, new Authority("URL_ROWSTATUS_CREATE", "/rowStatus/new"), adminRole);

		createAuthority(query, new Authority("URL_TEXTTEMPLATE_BROWSE", "/textTemplate"), adminRole);
		createAuthority(query, new Authority("URL_TEXTTEMPLATE_EDIT", "/textTemplate/edit"), adminRole);
		createAuthority(query, new Authority("URL_TEXTTEMPLATE_CREATE", "/textTemplate/new"), adminRole);


		createAuthority(query, new Authority("URL_PROFILE_VIEW", "/profile"), adminRole, userRole);
		createAuthority(query, new Authority("URL_PROFILE_EDIT", "/profile/edit"), adminRole, userRole);

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

	private void createAuthority(TypedQuery<Long> query, Authority authority, Role... roles){
		if(query.setParameter("name", authority.getName()).getSingleResult()==0){
			for(Role role : roles){
				authority.addRole(role);
			}
			entityManager.persist(authority);
		}
	}

}
