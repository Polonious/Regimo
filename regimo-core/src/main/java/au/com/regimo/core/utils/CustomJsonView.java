package au.com.regimo.core.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import au.com.regimo.core.form.XhrEvent;
import au.com.regimo.core.form.UserMessage;
import au.com.regimo.core.service.ReloadableMessageSource;

public class CustomJsonView extends MappingJackson2JsonView {

	private ReloadableMessageSource messageSource;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		for (Map.Entry<String, Object> entry : model.entrySet()) {
			if (entry.getValue() instanceof XhrEvent) {
				XhrEvent event = (XhrEvent) entry.getValue();
				for(UserMessage msg : event.getUserMessages()){
					msg.setText(messageSource.getMessage(msg.getCode(), null, request.getLocale()));
				}
			}
		}
		
		super.renderMergedOutputModel(model, request, response);
	}

	public void setMessageSource(ReloadableMessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
