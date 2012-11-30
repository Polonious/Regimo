package au.com.regimo.core.utils;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature ;

public class JsonObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public JsonObjectMapper() {
    	super();
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public void setPrettyPrint(boolean prettyPrint) {
        configure(SerializationFeature.INDENT_OUTPUT, prettyPrint);
    }
    
    public void setCustomDateFormat(String dateFormat){
    	if(dateFormat!=null && !dateFormat.trim().equals("")){
        	setDateFormat(new SimpleDateFormat(dateFormat));
        }
    }
}
