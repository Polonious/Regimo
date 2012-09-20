package au.com.regimo.server.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import au.com.regimo.core.domain.Authority;
import au.com.regimo.core.domain.Dashlet;
import au.com.regimo.core.domain.Role;
import au.com.regimo.core.form.ComboItem;
import au.com.regimo.core.service.AuthorityService;
import au.com.regimo.core.service.RoleService;

import com.google.common.collect.Lists;

@Named
@Transactional(readOnly = true)
public class ReferenceData {

	private RoleService roleService;
	private AuthorityService authorityService;

	public List<ComboItem> getDashletTypeOptions() {
		List<ComboItem> options = Lists.newArrayList();
		options.add(new ComboItem(Dashlet.TYPE_FREEMARKER));
		options.add(new ComboItem(Dashlet.TYPE_EXTRENAL));
		options.add(new ComboItem(Dashlet.TYPE_UNDEFINED));
		return options;
    }

	public Iterable<Role> getRoles(){
		return roleService.findAll();
	}

	public Iterable<Authority> getAuthorities(){
		return authorityService.findAll();
	}

	@Inject
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Inject
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

}
