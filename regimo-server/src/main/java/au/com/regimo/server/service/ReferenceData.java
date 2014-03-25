package au.com.regimo.server.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import au.com.regimo.cms.domain.Category;
import au.com.regimo.cms.service.CategoryService;
import au.com.regimo.core.domain.Authority;
import au.com.regimo.core.domain.Dashboard;
import au.com.regimo.core.domain.Dashlet;
import au.com.regimo.core.domain.Role;
import au.com.regimo.core.domain.RowStatus;
import au.com.regimo.core.domain.User;
import au.com.regimo.core.form.ComboItem;
import au.com.regimo.core.service.AuthorityService;
import au.com.regimo.core.service.DashboardService;
import au.com.regimo.core.service.DashletService;
import au.com.regimo.core.service.RoleService;
import au.com.regimo.core.service.RowStatusService;
import au.com.regimo.core.service.UserService;

import com.google.common.collect.Lists;

@Named
@Transactional(readOnly = true)
public class ReferenceData {

	private RoleService roleService;
	private UserService userService;
	private AuthorityService authorityService;
	private RowStatusService rowStatusService;
	private CategoryService categoryService;
	private DashboardService dashboardService;
	private DashletService dashletService;

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

	public Iterable<User> getUsers(){
		return userService.findAll();
	}

	public Iterable<Authority> getAuthorities(){
		return authorityService.findAll();
	}

	public Iterable<Category> getCategories(){
		return categoryService.findAll();
	}

	public Iterable<RowStatus> rowStatus(String statusObject){
		return rowStatusService.findByStatusObject(statusObject);
	}

	public Iterable<Dashboard> getDashboards(){
		return dashboardService.findAll();
	}

	public Iterable<Dashlet> getDashlets(){
		return dashletService.findAll();
	}

	@Inject
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Inject
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Inject
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	@Inject
	public void setRowStatusService(RowStatusService rowStatusService) {
		this.rowStatusService = rowStatusService;
	}

	@Inject
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Inject
	public void setDashboardService(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}

	@Inject
	public void setDashletService(DashletService dashletService) {
		this.dashletService = dashletService;
	}

}
