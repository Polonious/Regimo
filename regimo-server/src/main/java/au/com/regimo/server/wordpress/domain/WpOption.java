package au.com.regimo.server.wordpress.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wp_options database table.
 * 
 */
@Entity
@Table(name="wp_options")
public class WpOption implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="option_id")
	private Long optionId;

	private String autoload;

	@Column(name="blog_id")
	private int blogId;

	@Column(name="option_name")
	private String optionName;

    @Lob()
	@Column(name="option_value")
	private String optionValue;

    public WpOption() {
    }

	public Long getOptionId() {
		return this.optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public String getAutoload() {
		return this.autoload;
	}

	public void setAutoload(String autoload) {
		this.autoload = autoload;
	}

	public int getBlogId() {
		return this.blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getOptionName() {
		return this.optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionValue() {
		return this.optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

}