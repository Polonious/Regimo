package au.com.regimo.server.wordpress.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the wp_commentmeta database table.
 * 
 */
@Entity
@Table(name="wp_commentmeta")
public class WpCommentmeta implements Serializable {
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

	//bi-directional many-to-one association to WpComment
    @ManyToOne
	@JoinColumn(name="comment_id")
	private WpComment wpComment;

    public WpCommentmeta() {
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

	public WpComment getWpComment() {
		return this.wpComment;
	}

	public void setWpComment(WpComment wpComment) {
		this.wpComment = wpComment;
	}
	
}