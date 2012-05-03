package au.com.regimo.server.wordpress.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the wp_usermeta database table.
 * 
 */
@Entity
@Table(name="wp_usermeta")
public class WpUsermeta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="umeta_id")
	private Long umetaId;

	@Column(name="meta_key")
	private String metaKey;

    @Lob()
	@Column(name="meta_value")
	private String metaValue;

	//bi-directional many-to-one association to WpUser
    @ManyToOne
	@JoinColumn(name="user_id")
	private WpUser wpUser;

    public WpUsermeta() {
    }

	public Long getUmetaId() {
		return this.umetaId;
	}

	public void setUmetaId(Long umetaId) {
		this.umetaId = umetaId;
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

	public WpUser getWpUser() {
		return this.wpUser;
	}

	public void setWpUser(WpUser wpUser) {
		this.wpUser = wpUser;
	}
	
}