package au.com.regimo.core.utils;

public class Message {
	
	private final MessageType type;
	
	private final String text;

	/**
	 * Creates a new Message of a certain type consisting of the text provided.
	 */
	public Message(MessageType type, String text) {
		this.type = type;
		this.text = text;
	}

	/**
	 * Factory method for a success message.
	 */
	public static Message success(String text) {
		return new Message(MessageType.SUCCESS, text);
	}

	/**
	 * Factory method for an info message.
	 */
	public static Message info(String text) {
		return new Message(MessageType.INFO, text);
	}

	/**
	 * Factory method for a warning message.
	 */
	public static Message warning(String text) {
		return new Message(MessageType.WARNING, text);
	}

	/**
	 * Factory method for an error message.
	 */
	public static Message error(String text) {
		return new Message(MessageType.ERROR, text);
	}

	/**
	 * The type of message; such as info, warning, error, or success.
	 */
	public MessageType getType() {
		return type;
	}

	/**
	 * The info text.
	 */
	public String getText() {
		return text;
	}
	
	public String toString() {
		return type + ": " + text;
	}

}
