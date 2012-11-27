package au.com.regimo.web;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    public Collection<String> upload(@RequestParam("uploadedfiles[]") MultipartFile[] files) 
    		throws IOException {
		Collection<String> result = new LinkedList<String>();
		for(MultipartFile file : files){
	        if (file!=null && !file.isEmpty()) {
	        	result.add(service.save(file)+"/"+file.getOriginalFilename());
	        }
		}
        return result;
    }

	// via iFrame
	@RequestMapping(value = "/iframe", method = RequestMethod.POST)
    public void uploadFile(@RequestParam("uploadedfile") MultipartFile[] file, 
    		ModelMap modelMap) throws IOException {
        modelMap.addAttribute("document", 
        		json.writeValueAsString(upload(file)));
    }

	@RequestMapping(value = "/{id}/{filename:.+}")
	public void downloadFile(
			@PathVariable("id") String id, 
			@PathVariable("filename") String filename, 
			HttpServletResponse response) throws IOException {
		GridFSDBFile file = service.get(id);
		response.setContentType(file.getContentType());
		response.setContentLength(Ints.saturatedCast(file.getLength()));
		response.setHeader("Content-Disposition", String.format(
				"attachment; filename=\"%s\"", file.getFilename()));
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
