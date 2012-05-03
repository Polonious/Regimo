package au.com.regimo.core.service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sun.mail.util.BASE64DecoderStream;

import au.com.regimo.core.domain.Document;
import au.com.regimo.core.repository.DocumentRepository;

@Named
@Transactional(readOnly=true)
public class DocumentService {
	
	private DocumentRepository documentRepository;
	private String documentPath;
	
	@Transactional
	public Document save(MultipartFile file) throws IOException{
        return save(file.getOriginalFilename(), file.getBytes(), file.getContentType());
	}

	@Transactional
	public Document save(String filename, String content, String contentType) throws IOException{
		return save(filename, content.getBytes(), contentType);
	}
	
	@Transactional
	public Document saveBase64DecodeImage(String filename, String content, String contentType) throws IOException {
		return save(filename, content.getBytes(), contentType, true);
	}
	
	@Transactional
	public Document save(String filename, byte[] content, String contentType) throws IOException{
		return save(filename, content, contentType, false);
	}
	
	@Transactional
	public Document save(String filename, byte[] content, String contentType, boolean isBase64Decode) throws IOException{
        Document document = new Document();
        document.setOriginalFilename(filename);
        document.setIndexable(Boolean.FALSE);
        document.setFileSize(Long.valueOf(content.length));
        document.setContentType(contentType);
        Date currentDate = new Date();
        document.setDateAdded(currentDate);
        String storedFilename = currentDate.getTime()+"-"+RandomStringUtils.random(16,true,true)+"-"+content.length
        	+ "." + FilenameUtils.getExtension(filename.toLowerCase());
        document.setStoredFilename(storedFilename);
        documentRepository.save(document);
        storeFile(content, storedFilename, isBase64Decode);
    	return document;
	}
	
	public File retrieve(Document document){
		return new File(documentPath+document.getStoredFilename());
	}
	
	@Async
	private void storeFile(final byte[] bytes, final String storedFilename, boolean isBase64Decode) throws IOException{
		if(isBase64Decode){
			InputStream inStream = new BASE64DecoderStream(new ByteArrayInputStream(bytes));
			OutputStream outStream = new BufferedOutputStream(new FileOutputStream(documentPath+storedFilename));
			FileCopyUtils.copy(inStream, outStream);
		}
		else{
			FileCopyUtils.copy(bytes, new File(documentPath+storedFilename));
		}
	}
	
	@Inject
	public void setDocumentRepository(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}
	
	@Value("${document.repository}")
	public void setDocumentRepositoryPath(String documentRepositoryPath) {
		this.documentPath = documentRepositoryPath;
	}

}
