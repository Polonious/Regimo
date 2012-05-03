package au.com.regimo.core.utils;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

public class JsonObjectMapper extends ObjectMapper {

    public JsonObjectMapper() {
    	super();
        configure(Feature.FAIL_ON_EMPTY_BEANS, false);
    }

    public void setPrettyPrint(boolean prettyPrint) {
        configure(Feature.INDENT_OUTPUT, prettyPrint);
    }
    
    public void setCustomDateFormat(String dateFormat){
    	if(dateFormat!=null && !dateFormat.trim().equals("")){
        	setDateFormat(new SimpleDateFormat(dateFormat));
        }
    }
}
