package au.com.regimo.cms.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import au.com.regimo.core.domain.IdEntity;
import au.com.regimo.core.domain.User;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_article")
public class Article extends IdEntity {

	private static final long serialVersionUID = 1L;

	private String title;

	private String slug;

	@Column(length=8000)
	@Basic(fetch=FetchType.LAZY)
	private String summary;

	@Column(length=8000)
	@Basic(fetch=FetchType.LAZY)
	private String detail;

    @ManyToMany(targetEntity = Category.class)
    @JoinTable(name = "ArticleCategory", 
    	joinColumns = @JoinColumn(name = "articleId"), 
    	inverseJoinColumns = @JoinColumn(name = "categoryId"))
	private Set<Category> categories;

    @DateTimeFormat(iso=ISO.DATE)
    private Date publishedDate;
	
    @DateTimeFormat(iso=ISO.DATE)
	private Date startDate;
	
    @DateTimeFormat(iso=ISO.DATE)
	private Date endDate;
	
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name="authorId")
	private User author;

	private String image;

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

	@JsonIgnore
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}
