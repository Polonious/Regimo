package au.com.regimo.core.form;

import java.io.Serializable;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.google.common.collect.Lists;

public class XhrEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean success;

	private List<UserMessage> userMessages = Lists.newLinkedList();
	
	private Long id;

	public XhrEvent() {
		super();
	}

	public XhrEvent(BindingResult binding) {
		for(FieldError error : binding.getFieldErrors()){
			userMessages.add(UserMessage.error(getErrorCode(error), error.getField()));
			success = Boolean.FALSE;
		}
		for(ObjectError error: binding.getGlobalErrors()){
			userMessages.add(UserMessage.error(getErrorCode(error)));
			success = Boolean.FALSE;
		}
	}
	
	private String getErrorCode(ObjectError error){
		String code = error.getCode();
		String defaultMsg = error.getDefaultMessage();
		if(defaultMsg!=null && defaultMsg.startsWith("{") && defaultMsg.endsWith("}")){
			code = defaultMsg.substring(1, defaultMsg.length()-1);
		}
		return code;
	}

    public void addInfoMessage(String code) {
        this.userMessages.add(UserMessage.info(code));
	}

    public void addWarningMessage(String code) {
    	this.userMessages.add(UserMessage.warning(code));
    }
    
    public void addErrorMessage(String code) {
    	this.userMessages.add(UserMessage.error(code));
    	success = Boolean.FALSE;
    }

    public void addSuccessMessage(String code) {
        this.userMessages.add(UserMessage.success(code));
        success = Boolean.TRUE;
	}

    public boolean isValid(){
    	return success==null || success;
    }
    
	public boolean isSuccess() {
		return success!=null && success;
	}

	public List<UserMessage> getUserMessages() {
		return userMessages;
	}

	public void setUserMessages(List<UserMessage> userMessages) {
		this.userMessages = userMessages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
