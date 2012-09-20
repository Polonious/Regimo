package au.com.regimo.core.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.collect.Sets;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_authority")
public class Authority extends IdEntity {

	private static final long serialVersionUID = 1L;

    @Column(length = 64, nullable = false, unique = true)
    @NotEmpty
    private String name;

    @Column(length = 64)
    private String url;

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "AuthorityRole", joinColumns = @JoinColumn(name = "authorityId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;

    public Authority() {
    }

    public Authority(Long id) {
		this.id = id;
    }

    public Authority(String name) {
		this.name = name;
    }

    public Authority(String name, String url) {
    	this.name = name;
    	this.url = url;
    }

    public static Authority findAuthority(Long id){
    	Authority entity = new Authority();
    	entity.id = id;
    	return entity;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role){
		if(roles==null) roles = Sets.newHashSet(role);
		else roles.add(role);
	}

}
