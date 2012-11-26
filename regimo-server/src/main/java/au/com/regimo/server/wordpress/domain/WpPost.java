package au.com.regimo.server.wordpress.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the wp_posts database table.
 *
 */
@Entity
@Table(name="wp_posts")
public class WpPost implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TYPE_POST		= "post";
	public static final String TYPE_REVISION	= "revision";
	public static final String TYPE_PAGE		= "page";
	public static final String TYPE_ATTACHMENT	= "attachment";

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;

	@Column(name="comment_count")
	private BigInteger commentCount;

	@Column(name="comment_status")
	private String commentStatus;

	private String guid;

	@Column(name="menu_order")
	private int menuOrder;

	@Column(name="ping_status")
	private String pingStatus;

    @Lob()
	private String pinged;

    @Lob()
	@Column(name="post_content")
	private String postContent;

    @Lob()
	@Column(name="post_content_filtered")
	private String postContentFiltered;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="post_date")
	private Date postDate;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="post_date_gmt")
	private Date postDateGmt;

    @Lob()
	@Column(name="post_excerpt")
	private String postExcerpt;

	@Column(name="post_mime_type")
	private String postMimeType;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="post_modified")
	private Date postModified;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="post_modified_gmt")
	private Date postModifiedGmt;

	@Column(name="post_name")
	private String postName;

	@Column(name="post_parent")
	private BigInteger postParent;

	@Column(name="post_password")
	private String postPassword;

	@Column(name="post_status")
	private String postStatus;

    @Lob()
	@Column(name="post_title")
	private String postTitle;

	@Column(name="post_type")
	private String postType;

    @Lob()
	@Column(name="to_ping")
	private String toPing;

	//bi-directional many-to-one association to WpPostmeta
	@OneToMany(mappedBy="wpPost")
	private Set<WpPostmeta> wpPostmetas;

	//bi-directional many-to-one association to WpComment
	@OneToMany(mappedBy="wpPost")
	private Set<WpComment> wpComments;

	//bi-directional many-to-one association to WpUser
    @ManyToOne
	@JoinColumn(name="post_author")
	private WpUser wpUser;

	//bi-directional many-to-many association to WpTermTaxonomy
    @ManyToMany
	@JoinTable(
		name="wp_term_relationships"
		, joinColumns={
			@JoinColumn(name="object_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="term_taxonomy_id")
			}
		)
	private Set<WpTermTaxonomy> wpTermTaxonomies;

    public WpPost() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(BigInteger commentCount) {
		this.commentCount = commentCount;
	}

	public String getCommentStatus() {
		return this.commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	public String getGuid() {
		return this.guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public int getMenuOrder() {
		return this.menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getPingStatus() {
		return this.pingStatus;
	}

	public void setPingStatus(String pingStatus) {
		this.pingStatus = pingStatus;
	}

	public String getPinged() {
		return this.pinged;
	}

	public void setPinged(String pinged) {
		this.pinged = pinged;
	}

	public String getPostContent() {
		return this.postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostContentFiltered() {
		return this.postContentFiltered;
	}

	public void setPostContentFiltered(String postContentFiltered) {
		this.postContentFiltered = postContentFiltered;
	}

	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Date getPostDateGmt() {
		return this.postDateGmt;
	}

	public void setPostDateGmt(Date postDateGmt) {
		this.postDateGmt = postDateGmt;
	}

	public String getPostExcerpt() {
		return this.postExcerpt;
	}

	public void setPostExcerpt(String postExcerpt) {
		this.postExcerpt = postExcerpt;
	}

	public String getPostMimeType() {
		return this.postMimeType;
	}

	public void setPostMimeType(String postMimeType) {
		this.postMimeType = postMimeType;
	}

	public Date getPostModified() {
		return this.postModified;
	}

	public void setPostModified(Date postModified) {
		this.postModified = postModified;
	}

	public Date getPostModifiedGmt() {
		return this.postModifiedGmt;
	}

	public void setPostModifiedGmt(Date postModifiedGmt) {
		this.postModifiedGmt = postModifiedGmt;
	}

	public String getPostName() {
		return this.postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public BigInteger getPostParent() {
		return this.postParent;
	}

	public void setPostParent(BigInteger postParent) {
		this.postParent = postParent;
	}

	public String getPostPassword() {
		return this.postPassword;
	}

	public void setPostPassword(String postPassword) {
		this.postPassword = postPassword;
	}

	public String getPostStatus() {
		return this.postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	public String getPostTitle() {
		return this.postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostType() {
		return this.postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getToPing() {
		return this.toPing;
	}

	public void setToPing(String toPing) {
		this.toPing = toPing;
	}

	public Set<WpPostmeta> getWpPostmetas() {
		return this.wpPostmetas;
	}

	public void setWpPostmetas(Set<WpPostmeta> wpPostmetas) {
		this.wpPostmetas = wpPostmetas;
	}

	public Set<WpComment> getWpComments() {
		return this.wpComments;
	}

	public void setWpComments(Set<WpComment> wpComments) {
		this.wpComments = wpComments;
	}

	public WpUser getWpUser() {
		return this.wpUser;
	}

	public void setWpUser(WpUser wpUser) {
		this.wpUser = wpUser;
	}

	public Set<WpTermTaxonomy> getWpTermTaxonomies() {
		return this.wpTermTaxonomies;
	}

	public void setWpTermTaxonomies(Set<WpTermTaxonomy> wpTermTaxonomies) {
		this.wpTermTaxonomies = wpTermTaxonomies;
	}

}