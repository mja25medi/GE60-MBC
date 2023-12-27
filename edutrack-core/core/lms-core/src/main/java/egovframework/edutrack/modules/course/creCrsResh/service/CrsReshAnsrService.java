package egovframework.edutrack.modules.course.creCrsResh.service;

import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface CrsReshAnsrService {

	/**
	 * 개설 과정 설문 응답 목록 조회
	 */
	public abstract ProcessResultListVO<CrsReshAnsrVO> list(CrsReshAnsrVO VO) throws Exception;

	/**
	 * 개설 과정 설문 응답 페이징 목록 조회
	 */
	public abstract ProcessResultListVO<CrsReshAnsrVO> listPageing(
			CrsReshAnsrVO VO, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 개설 과정 설문 응답 페이징 목록 조회
	 */
	public abstract ProcessResultListVO<CrsReshAnsrVO> listPageing(
			CrsReshAnsrVO VO, int curPage, int listScale) throws Exception;

	/**
	 * 개설 과정 설문 응답 페이징 목록 조회
	 */
	public abstract ProcessResultListVO<CrsReshAnsrVO> listPageing(
			CrsReshAnsrVO VO, int curPage) throws Exception;

	/**
	 * 개설 과정 설문 응답 정보 조회
	 */
	public abstract ProcessResultVO<CrsReshAnsrVO> view(CrsReshAnsrVO VO) throws Exception;

	/**
	 * 개설 과정 설문 정보 등록
	 */
	public abstract ProcessResultVO<CrsReshAnsrVO> add(CrsReshAnsrVO VO) throws Exception;

	/**
	 * 개설 과정 설문 응답 정보 수정
	 */
	public abstract ProcessResultVO<CrsReshAnsrVO> edit(CrsReshAnsrVO VO) throws Exception;

	/**
	 * 개설 과정 설문 응답 정보 삭제
	 */
	public abstract ProcessResultVO<CrsReshAnsrVO> remove(CrsReshAnsrVO VO) throws Exception;

	/**
	 * 개설 과정 설문 의견 응답 목록 조회
	 */
	public abstract	ProcessResultListVO<CrsReshAnsrVO> listOpinion(CrsReshAnsrVO VO) throws Exception;

	/**
	 * 개설 과정 설문 응답 목록 조회  (설문번호 없이)
	 */
	public abstract ProcessResultListVO<CrsReshAnsrVO> list_noReshSn(CrsReshAnsrVO VO) throws Exception;

	/**
	 * 개설 과정 설문 응답 건수
	 */
	public abstract ProcessResultVO<CrsReshAnsrVO> listCount(CrsReshAnsrVO VO) throws Exception;

	/**
	 * 개설과정관리/설문결과 다운로드
	 * @return  ProcessResultVO
	 */
	public abstract void listExcelDownload(CreCrsReshVO creCrsReshVO, String sheetName,
			String labelName, String condition, String excelHeader, HashMap<String, String> titleMap,
			HttpServletRequest request, ServletOutputStream os) throws ServiceProcessException;
}