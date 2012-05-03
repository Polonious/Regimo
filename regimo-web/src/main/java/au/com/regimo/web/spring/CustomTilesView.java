package au.com.regimo.web.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.ContextExposingHttpServletRequest;
import org.springframework.web.servlet.view.tiles2.TilesView;

import com.google.common.collect.Sets;

public class CustomTilesView extends TilesView {

	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpServletRequest requestToExpose = new ContextExposingHttpServletRequest(
				request, getWebApplicationContext(), Sets.newHashSet("referenceData"));
		super.renderMergedOutputModel(model, requestToExpose, response);
	}
	
}
