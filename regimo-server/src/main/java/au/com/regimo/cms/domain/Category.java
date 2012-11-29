package au.com.regimo.cms.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import au.com.regimo.core.domain.IdEntity;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_category")
public class Category extends IdEntity {

	private static final long serialVersionUID = 1L;

    private String name;

    private String slug;

    @ManyToMany(mappedBy = "categories", targetEntity =  Article.class)
    private Set<Article> articles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

}
