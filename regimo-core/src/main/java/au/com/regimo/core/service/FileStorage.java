package au.com.regimo.core.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Named;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.sun.mail.util.BASE64DecoderStream;

@Named
public class FileStorage {

	private GridFsOperations gridFs;

	public String save(MultipartFile file) throws IOException{
        return save(file.getOriginalFilename(), file.getBytes(), file.getContentType());
	}

	public String save(String filename, String content, String contentType) throws IOException {
		return save(filename, content.getBytes(), contentType);
	}
	
	public String saveBase64Decode(String filename, String content, String contentType) throws IOException {
		return save(filename, content.getBytes(), contentType, true);
	}
	
	public String save(String filename, byte[] content, String contentType) throws IOException{
		return save(filename, content, contentType, false);
	}

	private String save(final String filename, final byte[] bytes, String contentType, boolean isBase64Decode) throws IOException {
		InputStream byteStream = new ByteArrayInputStream(bytes);
		InputStream inStream = byteStream;
		if(isBase64Decode){
			inStream = new BASE64DecoderStream(byteStream);
		}
		DBObject metaData = new BasicDBObject();
		metaData.put("contentType", contentType);
		GridFSFile file = gridFs.store(inStream, filename);
		inStream.close();
		return file.getId().toString();
	}
	
	public GridFSDBFile get(String id) {
		return gridFs.findOne(new Query(Criteria.where("_id").is(new ObjectId(id))));
	}

	@Inject
	public void setGridFs(GridFsOperations gridFs) {
		this.gridFs = gridFs;
	}	
	
}
