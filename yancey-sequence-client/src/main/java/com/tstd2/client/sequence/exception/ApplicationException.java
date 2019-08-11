package com.tstd2.client.sequence.exception;

import java.util.List;

/**
 * @author yancey
 */
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 2030063376333005400L;
	
	private List<String> messages = null;

    public ApplicationException() {
        super();
    }

    public ApplicationException(List<String> messages) {
        super();
        this.messages = messages;
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
