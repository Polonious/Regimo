package au.com.regimo.core.form;

public enum UserMessageType {
	
	/**
	 * The message is informative in nature, like a note or notice.
	 */
	info, 

	/**
	 * The message indicates that an action initiated by the user was performed successfully.
	 */
	success, 
	
	/**
	 * The message warns the user something is not quite right.
	 * Corrective action is generally recommended but not required.
	 */
	warning, 
	
	/**
	 * The message reports an error condition that needs to be addressed by the user.
	 */
	error;
	
}