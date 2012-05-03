package au.com.regimo.core.form;

import java.io.Serializable;
import java.util.Comparator;

public class ComboItem implements Serializable, Comparator<ComboItem> {

	private static final long serialVersionUID = 1L;

	private String label;
	private String value;
	private String description;
	private Boolean selected;
	private Boolean disabled = Boolean.FALSE;

	public ComboItem() {
	}

	public ComboItem(String label) {
		super();
		this.label = label;
		this.value = label;
	}

	public ComboItem(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}

	public ComboItem(String label, Long value) {
		super();
		this.label = label;
		this.value = value.toString();
	}
	
	public ComboItem(String label, String value, String description) {
		super();
		this.label = label;
		this.value = value;
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public int compare(ComboItem o1, ComboItem o2) {
		return o1.getLabel().compareTo(o2.getLabel());
	}

}