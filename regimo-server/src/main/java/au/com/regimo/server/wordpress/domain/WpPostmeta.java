package au.com.regimo.server.wordpress.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the wp_postmeta database table.
 * 
 */
@Entity
@Table(name="wp_postmeta")
public class WpPostmeta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="meta_id")
	private Long metaId;

	@Column(name="meta_key")
	private String metaKey;

    @Lob()
	@Column(name="meta_value")
	private String metaValue;

	//bi-directional many-to-one association to WpPost
    @ManyToOne
	@JoinColumn(name="post_id")
	private WpPost wpPost;

    public WpPostmeta() {
    }

	public Long getMetaId() {
		return this.metaId;
	}

	public void setMetaId(Long metaId) {
		this.metaId = metaId;
	}

	public String getMetaKey() {
		return this.metaKey;
	}

	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}

	public String getMetaValue() {
		return this.metaValue;
	}

	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}

	public WpPost getWpPost() {
		return this.wpPost;
	}

	public void setWpPost(WpPost wpPost) {
		this.wpPost = wpPost;
	}
	
}