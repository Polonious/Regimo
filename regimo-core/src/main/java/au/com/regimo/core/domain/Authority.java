package au.com.regimo.core.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_authority")
public class Authority extends IdEntity {

	private static final long serialVersionUID = 1L;

    @Column(length = 255, nullable = false)  
    private String name;

    @Column(length = 64, nullable = false)  
    private String type;
    
    @Column(length = 255, nullable = false, unique = true) 
    private String value;

    @ManyToMany(targetEntity = Role.class)  
    @JoinTable(name = "AuthorityRole", joinColumns = @JoinColumn(name = "authorityId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;

    public Authority() {
    }

    public Authority(String name, String type, String value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
