package egovframework.edutrack.comm.exception;


public class SessionBrokenException
		extends AuthorityException {

	private static final String	DEFAULT_MESSAGE	= "common.message.framework.auth.sessionend";
	private static final long	serialVersionUID	= -2090100780529696444L;
	

	public SessionBrokenException() {
		super(DEFAULT_MESSAGE);
	}

	public SessionBrokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public SessionBrokenException(String message) {
		super(message);
	}

	public SessionBrokenException(Throwable cause) {
		super(DEFAULT_MESSAGE, cause);
	}

}
