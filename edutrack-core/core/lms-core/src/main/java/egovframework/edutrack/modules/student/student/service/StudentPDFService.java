package egovframework.edutrack.modules.student.student.service;

import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;

public interface StudentPDFService {

	/**
	 * 수강생/수료생/신청자 명단 다운로드
	 * @param eduResultVO
	 * @param createCourseVO
	 * @param os
	 * @throws ServiceProcessException
	 */
	public abstract void listPDFDownload(StudentVO studentVO,
			CreateCourseVO createCourseVO, String sheetName,
			String labelName, String condition, String excelHeader,
			ArrayList<String> titleList, HttpServletRequest request,
			OutputStream os) throws ServiceProcessException;

}