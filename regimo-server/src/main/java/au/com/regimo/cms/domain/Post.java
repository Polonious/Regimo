package au.com.regimo.cms.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import au.com.regimo.core.domain.IdEntity;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_post")
public class Post extends IdEntity {

	private static final long serialVersionUID = 1L;

	private String title;

	private String slug;

	@Column
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private String summary;

	@Column
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private String detail;

    @ManyToMany(targetEntity = Category.class)
    @JoinTable(name = "PostCategory", joinColumns = @JoinColumn(name = "postId"), inverseJoinColumns = @JoinColumn(name = "categoryId"))
	private Set<Category> categories;

	private Date createdDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
