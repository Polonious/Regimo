package au.com.regimo.web;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import au.com.regimo.core.service.FileStorage;
import au.com.regimo.core.utils.JsonObjectMapper;

import com.google.common.primitives.Ints;
import com.mongodb.gridfs.GridFSDBFile;

@Controller
@RequestMapping("/document")
public class DocumentController {

	private FileStorage service;
	private JsonObjectMapper json;

	// via Html5
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
    public Collection<String> upload(MultipartHttpServletRequest request) throws IOException {
		Collection<String> result = new LinkedList<String>();
		for(Map.Entry<String,MultipartFile> entry : request.getFileMap().entrySet()){
			MultipartFile file = entry.getValue();
	        if (file!=null && !file.isEmpty()) {
	        	result.add(service.save(file)+"/"+file.getOriginalFilename());
	        }
		}
        return result;
    }

	// via iFrame
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadFile(MultipartHttpServletRequest request,
    		ModelMap modelMap) throws IOException {
        modelMap.addAttribute("document",
        		json.writeValueAsString(upload(request)));
    }

	@RequestMapping(value = "/{id}/{filename:.+}")
	public void downloadFile(
			@PathVariable("id") String id,
			@PathVariable("filename") String filename,
			WebRequest webRequest,
			HttpServletResponse response) throws IOException {
		GridFSDBFile file = service.get(id);
		response.setContentType(file.getContentType());
		response.setContentLength(Ints.saturatedCast(file.getLength()));

		// download required
		if(webRequest.getParameter("download")!=null){
			response.setHeader("Content-Disposition", String.format(
					"attachment; filename=\"%s\"", file.getFilename()));
		}
		file.writeTo(response.getOutputStream());
	}

	@Inject
	public void setService(FileStorage service) {
		this.service = service;
	}

	@Inject
	public void setJson(JsonObjectMapper json) {
		this.json = json;
	}

}
