package egovframework.edutrack.modules.student.student.service;

/**
 * 학생을 등록하는 과정에서 생기는 예외.
 * @author SungKook
 */
public class ValidateStudentException extends Exception {

	private static final long	serialVersionUID	= -1395597998544590054L;

	public ValidateStudentException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ValidateStudentException(String msg) {
		super(msg);
	}

}
