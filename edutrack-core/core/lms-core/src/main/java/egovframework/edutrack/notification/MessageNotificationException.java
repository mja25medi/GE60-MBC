package egovframework.edutrack.notification;

import egovframework.edutrack.comm.exception.ServiceProcessException;



public class MessageNotificationException
		extends ServiceProcessException {

	private static final long	serialVersionUID	= 1L;

	public MessageNotificationException() {
		super();
	}

	public MessageNotificationException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageNotificationException(String message) {
		super(message);
	}

	public MessageNotificationException(Throwable cause) {
		super(cause);
	}
	
}
