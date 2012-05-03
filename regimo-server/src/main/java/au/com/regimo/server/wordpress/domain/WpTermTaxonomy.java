package au.com.regimo.server.wordpress.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the wp_term_taxonomy database table.
 * 
 */
@Entity
@Table(name="wp_term_taxonomy")
public class WpTermTaxonomy implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String AXONOMY_CATEGORY			= "category";
	public static final String AXONOMY_POST_TAG			= "post_tag";
	public static final String AXONOMY_LINK_CATEGORY	= "link_category";

	@Id
	@GeneratedValue
	@Column(name="term_taxonomy_id")
	private Long termTaxonomyId;

	private BigInteger count;

    @Lob()
	private String description;

	private BigInteger parent;

	private String taxonomy;

	//bi-directional many-to-one association to WpTerm
    @ManyToOne
	@JoinColumn(name="term_id")
	private WpTerm wpTerm;

	//bi-directional many-to-many association to WpPost
	@ManyToMany(mappedBy="wpTermTaxonomies")
	private Set<WpPost> wpPosts;

    public WpTermTaxonomy() {
    }

	public Long getTermTaxonomyId() {
		return this.termTaxonomyId;
	}

	public void setTermTaxonomyId(Long termTaxonomyId) {
		this.termTaxonomyId = termTaxonomyId;
	}

	public BigInteger getCount() {
		return this.count;
	}

	public void setCount(BigInteger count) {
		this.count = count;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigInteger getParent() {
		return this.parent;
	}

	public void setParent(BigInteger parent) {
		this.parent = parent;
	}

	public String getTaxonomy() {
		return this.taxonomy;
	}

	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}

	public WpTerm getWpTerm() {
		return this.wpTerm;
	}

	public void setWpTerm(WpTerm wpTerm) {
		this.wpTerm = wpTerm;
	}
	
	public Set<WpPost> getWpPosts() {
		return this.wpPosts;
	}

	public void setWpPosts(Set<WpPost> wpPosts) {
		this.wpPosts = wpPosts;
	}
	
}