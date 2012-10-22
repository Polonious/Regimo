package au.com.regimo.core.form;

import java.io.Serializable;

public class UserMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private UserMessageType type;
    
	private String text;

	private String code;
	
	private String field;

	private UserMessage(UserMessageType type, String code) {
            super();
            this.type = type;
            this.code = code;
    }
    
	private UserMessage(UserMessageType type, String code, String field) {
            this(type, code);
            this.field = field;
    }
    
    public static UserMessage success(String code){
    	return new UserMessage(UserMessageType.success, code);
    }

    public static UserMessage info(String code){
    	return UserMessage.info(code, null);
    }
    
    public static UserMessage warning(String code){
    	return UserMessage.warning(code, null);
    }
    
    public static UserMessage error(String code){
    	return UserMessage.error(code, null);
    }
    
    public static UserMessage info(String code, String field){
    	return new UserMessage(UserMessageType.info, code, field);
    }
    
    public static UserMessage warning(String code, String field){
    	return new UserMessage(UserMessageType.warning, code, field);
    }
    
    public static UserMessage error(String code, String field){
    	return new UserMessage(UserMessageType.error, code, field);
    }
    
    public String getText() {
    	return text;
    }
    
    public void setText(String text) {
    	this.text = text;
    }
    
    public UserMessageType getType() {
    	return type;
    }
    
    public void setType(UserMessageType type) {
    	this.type = type;
    }

    public String getField() {
    	return field;
    }

    public void setField(String field) {
    	this.field = field;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
