package au.com.regimo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	@RequestMapping(value="endpoints", method = RequestMethod.GET)
	public void getEndPointsInView(Model model) {
	    model.addAttribute("endpoints", requestMappingHandlerMapping.getHandlerMethods().keySet());
	}

	@Autowired
	@Qualifier("org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#0")
	public void setRequestMappingHandlerMapping(
			RequestMappingHandlerMapping requestMappingHandlerMapping) {
		this.requestMappingHandlerMapping = requestMappingHandlerMapping;
	}

}
