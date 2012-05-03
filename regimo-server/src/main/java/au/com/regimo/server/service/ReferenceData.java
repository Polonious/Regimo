package au.com.regimo.server.service;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import au.com.regimo.core.domain.Dashlet;
import au.com.regimo.core.form.ComboItem;

@Named
@Transactional(readOnly = true)
public class ReferenceData {

	public List<ComboItem> getDashletTypeOptions() {
		List<ComboItem> options = Lists.newArrayList();
		options.add(new ComboItem(Dashlet.TYPE_FREEMARKER));
		options.add(new ComboItem(Dashlet.TYPE_EXTRENAL));
		options.add(new ComboItem(Dashlet.TYPE_UNDEFINED));
		return options;
    }
	
}
