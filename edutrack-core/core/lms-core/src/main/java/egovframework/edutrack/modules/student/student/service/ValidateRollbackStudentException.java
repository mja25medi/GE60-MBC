package egovframework.edutrack.modules.student.student.service;

import org.springframework.dao.DataAccessException;

/**
 * 학생을 등록하는 과정에서 생기는 예외.
 * 롤백을 위해 DataAccessException을 상속 받음.
 * @author SungKook
 *
 */
public class ValidateRollbackStudentException extends DataAccessException {

	private static final long	serialVersionUID	= -1395597998544590054L;

	public ValidateRollbackStudentException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ValidateRollbackStudentException(String msg) {
		super(msg);
	}

}
