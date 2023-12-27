package egovframework.edutrack.modules.log.tchactstatus.service;

import java.io.OutputStream;
import java.util.ArrayList;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface LogTchActStatusService {

	/**
	 * 교수자/투터의 목록을 가져온다.
	 * (페이징 정보 포함)
	 * @param LogTchActStatusVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListDTO<BbsAtclDTO>
	 */
	public abstract ProcessResultListVO<LogTchActStatusVO> listPageing(LogTchActStatusVO vo) throws Exception ;

	/**
	 * 교수자/투터의 목록을 가져온다.
	 * @param LogTchActStatusVO
	 * @return ProcessResultListDTO<BbsAtclDTO>
	 */
	public abstract ProcessResultListVO<LogTchActStatusVO> list(
			LogTchActStatusVO vo) throws Exception ;

	/**
	 * 해당 기간에 속하는 과정의 목록을 가져온다.
	 * 선택된 교수자의 과정만.
	 * @param LogTchActStatusVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListDTO<BbsAtclDTO>
	 */
	public abstract ProcessResultListVO<LogTchActStatusDetailVO> listCoursePageing(
			LogTchActStatusDetailVO vo) throws Exception ;

	/**
	 * 해당 기간에 속하는 과정의 목록을 가져온다.
	 * 선택된 교수자의 과정만.
	 * @param LogTchActStatusVO
	 * @return ProcessResultListDTO<BbsAtclDTO>
	 */
	public abstract ProcessResultListVO<LogTchActStatusDetailVO> listCourse(
			LogTchActStatusDetailVO dto) throws Exception ;

	/**
	 * 강사의 단일행 정보를 검색하여 반환한다.
	 * @param teacherInfoDTO
	 * @return
	 */
	public abstract ProcessResultVO<LogTchActStatusVO> view(
			LogTchActStatusVO vo) throws Exception ;

	/**
	 * 해당 기간에 속하는 과정의 접속로그를 가져온다
	 * 선택된 교수자의 과정만.
	 * @param LogTchActStatusVO
	 * @return ProcessResultListDTO<BbsAtclDTO>
	 */
	public abstract ProcessResultListVO<LogTchActStatusDetailVO> listCourseStatistics(
			LogTchActStatusDetailVO vo) throws Exception ;
	
	
	/**
	 * 강사활동내역 다운로드
	* @param eduCourseStatusDTO
	* @param orgNm
	 * @param os
	 * @throws ServiceProcessException
	 */
	public abstract void listExcelDownload(LogTchActStatusVO vo, String orgNm, ArrayList<String> titleList, OutputStream os) throws Exception;

}