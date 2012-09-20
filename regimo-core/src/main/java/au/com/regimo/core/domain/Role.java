package au.com.regimo.core.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_role")
public class Role extends IdEntity {

	private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;

    @Column
    private String description;

    @ManyToMany(mappedBy = "roles", targetEntity = Authority.class)
    private Set<Authority> authorities;

    public Role() {
    }

	public Role(String name, String description) {
		this.name = name;
		this.description = description;
	}

    public static Role findRole(Long id){
    	Role entity = new Role();
    	entity.id = id;
    	return entity;
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

	@JsonIgnore
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

}
