package au.com.regimo.server.service;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import au.com.regimo.core.domain.Dashlet;
import au.com.regimo.core.domain.Role;
import au.com.regimo.core.form.ComboItem;
import au.com.regimo.core.repository.RoleRepository;

import com.google.common.collect.Lists;

@Named
@Transactional(readOnly = true)
public class ReferenceData {
	
	@Autowired
	private RoleRepository roleRepository;

	public List<ComboItem> getDashletTypeOptions() {
		List<ComboItem> options = Lists.newArrayList();
		options.add(new ComboItem(Dashlet.TYPE_FREEMARKER));
		options.add(new ComboItem(Dashlet.TYPE_EXTRENAL));
		options.add(new ComboItem(Dashlet.TYPE_UNDEFINED));
		return options;
    }
	
	 public List<ComboItem> getUserRoleOptions() {
	    	List<ComboItem>  options = Lists.newArrayList();
	    	for(Role role : roleRepository.findAll()){
	    			options.add(new ComboItem(role.getName()));
	    		}
			return options;
		}
}
