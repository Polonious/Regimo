package au.com.regimo.core.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.JoinTable;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_menu")
public class Menu extends IdEntity implements IAuditable {

	private static final long serialVersionUID = 1L;
		
	@Column(length = 64)
	private String name;
	
    
	@Column(length = 8000)
	private String label;
	
	@ManyToMany(targetEntity = Role.class)  
    @JoinTable(name = "rolemenu", joinColumns = @JoinColumn(name = "menuId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;

	@OneToMany(mappedBy = "menu")
	private List<MenuItem> menuItems;
	
	public String getName() {
		return name;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}

