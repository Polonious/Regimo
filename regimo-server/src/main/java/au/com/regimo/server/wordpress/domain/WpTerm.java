package au.com.regimo.server.wordpress.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the wp_terms database table.
 * 
 */
@Entity
@Table(name="wp_terms")
public class WpTerm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="term_id")
	private Long termId;

	private String name;

	private String slug;

	@Column(name="term_group")
	private BigInteger termGroup;

	//bi-directional many-to-one association to WpTermTaxonomy
	@OneToMany(mappedBy="wpTerm")
	private Set<WpTermTaxonomy> wpTermTaxonomies;
	
	private List<WpPost> wpPosts = new LinkedList<WpPost>();

    public WpTerm() {
    }

	public Long getTermId() {
		return this.termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public BigInteger getTermGroup() {
		return this.termGroup;
	}

	public void setTermGroup(BigInteger termGroup) {
		this.termGroup = termGroup;
	}

	public Set<WpTermTaxonomy> getWpTermTaxonomies() {
		return this.wpTermTaxonomies;
	}

	public void setWpTermTaxonomies(Set<WpTermTaxonomy> wpTermTaxonomies) {
		this.wpTermTaxonomies = wpTermTaxonomies;
	}

	public List<WpPost> getWpPosts() {
		return wpPosts;
	}

	public void setWpPosts(List<WpPost> wpPosts) {
		this.wpPosts = wpPosts;
	}
	
}