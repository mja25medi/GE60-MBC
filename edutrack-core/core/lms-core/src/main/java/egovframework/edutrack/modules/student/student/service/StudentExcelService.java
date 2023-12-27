package egovframework.edutrack.modules.student.student.service;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;

public interface StudentExcelService {


	/**
	 * 엑셀파일을 로딩하여 상태를 체크하고 리턴한다.
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional
	public abstract ProcessResultListVO<StudentExcelVO> listStudentExcel(String crsCreCd, String fileName,
			String filePath) throws ServiceProcessException, ValidateRollbackStudentException;

	/**
	 * 교육생 엑셀 업로드
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional
	public abstract ProcessResultVO<StudentVO> addStudentExcel(List<StudentExcelVO> studentList, StudentVO sdto)
			throws ValidateStudentException;

	/**
	 * 출석부 다운로드
	 * @param eduResultVO
	 * @param createCourseVO
	 * @param os
	 * @throws ServiceProcessException
	 */
	public abstract void listExcelDownloadAttend(StudentVO studentVO, CreateCourseVO createCourseVO,
			OutputStream os) throws ServiceProcessException;


	/**
	 * 수강생/수료생/신청자 명단 다운로드
	 * @param eduResultVO
	 * @param createCourseVO
	 * @param os
	 * @throws ServiceProcessException
	 */
	public abstract void listExcelDownload(StudentVO studentVO, CreateCourseVO createCourseVO, String sheetName, String labelName,
			String condition, String excelHeader, ArrayList<String> titleList, HttpServletRequest request, OutputStream os, HashMap<String, String> titles)
			throws ServiceProcessException;
	
	public abstract ProcessResultVO<StudentVO> addStudentPaymentExcel(StudentVO vo, String fileName, String filePath);
	
	/**
	 * 자격증 응시생 샘플 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws ServiceProcessException
	 */
	public abstract void sampleExcelDownload(HashMap<String, String> titles, OutputStream os) throws ServiceProcessException;
	
	/**
	 * 자격증 응시생 엑셀업로드
	 * @throws ServiceProcessException
	 */
	public abstract ProcessResultListVO<StudentExcelVO> excelUploadValidationCheck(String fileName, String filePath) throws ServiceProcessException;
	
	/**
	 * 자격증 응시생 엑셀 업로드 수강데이터 등록
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional
	public abstract ProcessResultVO<StudentVO> addCertStudentExcel(List<StudentExcelVO> studentList, StudentVO sdto) throws ValidateStudentException;
}