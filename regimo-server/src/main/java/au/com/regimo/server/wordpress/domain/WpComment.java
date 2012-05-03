package au.com.regimo.server.wordpress.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the wp_comments database table.
 * 
 */
@Entity
@Table(name="wp_comments")
public class WpComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long comment_ID;

	@Column(name="comment_agent")
	private String commentAgent;

	@Column(name="comment_approved")
	private String commentApproved;

    @Lob()
	@Column(name="comment_author")
	private String commentAuthor;

	@Column(name="comment_author_email")
	private String commentAuthorEmail;

	private String comment_author_IP;

	@Column(name="comment_author_url")
	private String commentAuthorUrl;

    @Lob()
	@Column(name="comment_content")
	private String commentContent;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="comment_date")
	private Date commentDate;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="comment_date_gmt")
	private Date commentDateGmt;

	@Column(name="comment_karma")
	private int commentKarma;

	@Column(name="comment_parent")
	private BigInteger commentParent;

	@Column(name="comment_type")
	private String commentType;

	@Column(name="user_id")
	private BigInteger userId;

	//bi-directional many-to-one association to WpCommentmeta
	@OneToMany(mappedBy="wpComment")
	private Set<WpCommentmeta> wpCommentmetas;

	//bi-directional many-to-one association to WpPost
    @ManyToOne
	@JoinColumn(name="comment_post_ID")
	private WpPost wpPost;

    public WpComment() {
    }

	public Long getComment_ID() {
		return this.comment_ID;
	}

	public void setComment_ID(Long comment_ID) {
		this.comment_ID = comment_ID;
	}

	public String getCommentAgent() {
		return this.commentAgent;
	}

	public void setCommentAgent(String commentAgent) {
		this.commentAgent = commentAgent;
	}

	public String getCommentApproved() {
		return this.commentApproved;
	}

	public void setCommentApproved(String commentApproved) {
		this.commentApproved = commentApproved;
	}

	public String getCommentAuthor() {
		return this.commentAuthor;
	}

	public void setCommentAuthor(String commentAuthor) {
		this.commentAuthor = commentAuthor;
	}

	public String getCommentAuthorEmail() {
		return this.commentAuthorEmail;
	}

	public void setCommentAuthorEmail(String commentAuthorEmail) {
		this.commentAuthorEmail = commentAuthorEmail;
	}

	public String getComment_author_IP() {
		return this.comment_author_IP;
	}

	public void setComment_author_IP(String comment_author_IP) {
		this.comment_author_IP = comment_author_IP;
	}

	public String getCommentAuthorUrl() {
		return this.commentAuthorUrl;
	}

	public void setCommentAuthorUrl(String commentAuthorUrl) {
		this.commentAuthorUrl = commentAuthorUrl;
	}

	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Date getCommentDateGmt() {
		return this.commentDateGmt;
	}

	public void setCommentDateGmt(Date commentDateGmt) {
		this.commentDateGmt = commentDateGmt;
	}

	public int getCommentKarma() {
		return this.commentKarma;
	}

	public void setCommentKarma(int commentKarma) {
		this.commentKarma = commentKarma;
	}

	public BigInteger getCommentParent() {
		return this.commentParent;
	}

	public void setCommentParent(BigInteger commentParent) {
		this.commentParent = commentParent;
	}

	public String getCommentType() {
		return this.commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public BigInteger getUserId() {
		return this.userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public Set<WpCommentmeta> getWpCommentmetas() {
		return this.wpCommentmetas;
	}

	public void setWpCommentmetas(Set<WpCommentmeta> wpCommentmetas) {
		this.wpCommentmetas = wpCommentmetas;
	}
	
	public WpPost getWpPost() {
		return this.wpPost;
	}

	public void setWpPost(WpPost wpPost) {
		this.wpPost = wpPost;
	}
	
}