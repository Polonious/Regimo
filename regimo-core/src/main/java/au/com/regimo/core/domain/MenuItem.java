package au.com.regimo.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="SEQ_STORE", sequenceName = "seq_menu_item")
public class MenuItem extends IdEntity implements IAuditable {

	private static final long serialVersionUID = 1L;
		
	@Column(length = 64)
	private String name;
	
    
	@Column(length = 8000)
	private String text;
	
	@ManyToOne
    @JoinColumn(name="menuId")
	private Menu menu;

	@Column(length = 255)
	private String hyperLink;
	
	public String getHyperLink() {
		return hyperLink;
	}

	public void setHyperLink(String hyperLink) {
		this.hyperLink = hyperLink;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}


	
}

