package au.com.regimo.core.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_role")
public class Role extends IdEntity {

	private static final long serialVersionUID = 1L;
	
    @Column(nullable = false, unique = true)  
    private String name;

    @Column
    private String description;

    @ManyToMany(mappedBy = "roles", targetEntity = Authority.class)  
    private Set<Authority> authorities;  
    
    @ManyToMany(mappedBy = "roles", targetEntity = Menu.class)
    private Set<Menu> menus;
    
	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	public Role(String name, String description) {
		this();
		this.name = name;
		this.description = description;
	}

	public Role() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

}
