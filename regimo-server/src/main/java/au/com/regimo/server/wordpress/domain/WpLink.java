package au.com.regimo.server.wordpress.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the wp_links database table.
 * 
 */
@Entity
@Table(name="wp_links")
public class WpLink implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="link_id")
	private Long linkId;

	@Column(name="link_description")
	private String linkDescription;

	@Column(name="link_image")
	private String linkImage;

	@Column(name="link_name")
	private String linkName;

    @Lob()
	@Column(name="link_notes")
	private String linkNotes;

	@Column(name="link_owner")
	private BigInteger linkOwner;

	@Column(name="link_rating")
	private int linkRating;

	@Column(name="link_rel")
	private String linkRel;

	@Column(name="link_rss")
	private String linkRss;

	@Column(name="link_target")
	private String linkTarget;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="link_updated")
	private Date linkUpdated;

	@Column(name="link_url")
	private String linkUrl;

	@Column(name="link_visible")
	private String linkVisible;

    public WpLink() {
    }

	public Long getLinkId() {
		return this.linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	public String getLinkDescription() {
		return this.linkDescription;
	}

	public void setLinkDescription(String linkDescription) {
		this.linkDescription = linkDescription;
	}

	public String getLinkImage() {
		return this.linkImage;
	}

	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
	}

	public String getLinkName() {
		return this.linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkNotes() {
		return this.linkNotes;
	}

	public void setLinkNotes(String linkNotes) {
		this.linkNotes = linkNotes;
	}

	public BigInteger getLinkOwner() {
		return this.linkOwner;
	}

	public void setLinkOwner(BigInteger linkOwner) {
		this.linkOwner = linkOwner;
	}

	public int getLinkRating() {
		return this.linkRating;
	}

	public void setLinkRating(int linkRating) {
		this.linkRating = linkRating;
	}

	public String getLinkRel() {
		return this.linkRel;
	}

	public void setLinkRel(String linkRel) {
		this.linkRel = linkRel;
	}

	public String getLinkRss() {
		return this.linkRss;
	}

	public void setLinkRss(String linkRss) {
		this.linkRss = linkRss;
	}

	public String getLinkTarget() {
		return this.linkTarget;
	}

	public void setLinkTarget(String linkTarget) {
		this.linkTarget = linkTarget;
	}

	public Date getLinkUpdated() {
		return this.linkUpdated;
	}

	public void setLinkUpdated(Date linkUpdated) {
		this.linkUpdated = linkUpdated;
	}

	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkVisible() {
		return this.linkVisible;
	}

	public void setLinkVisible(String linkVisible) {
		this.linkVisible = linkVisible;
	}

}