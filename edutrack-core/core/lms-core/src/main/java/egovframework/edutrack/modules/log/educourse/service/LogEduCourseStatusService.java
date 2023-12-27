package egovframework.edutrack.modules.log.educourse.service;

import java.io.OutputStream;
import java.util.ArrayList;


import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface LogEduCourseStatusService {

	/**
	 * 팀별 /년도 월에 대한 과정 운영 현황 목록
	 * @param LogEduTeamStatusVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogEduTeamStatusVO> listTeamStatus(
			LogEduTeamStatusVO vo) throws Exception;

	/**
	 * 팀별, 년,월별 개설과정 목록 
	 * @param LogEduCourseStatusVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogEduCourseStatusVO> listTeamStatus(
			LogEduCourseStatusVO vo) throws Exception;

	/**
	 * 팀별 과정 운영 현황 - 개설 과정 목록을 반환한다.
	 * @param iQuizStareDTO
	 * @return
	 */
	public abstract ProcessResultListVO<LogEduCourseStatusVO> listCourseStatus(LogEduCourseStatusVO vo) throws Exception;
	/**
	 * 팀별 과정 운영 현황 - 개설 과정 목록을 반환한다.(페이징)
	 * @param iQuizStareDTO
	 * @return
	 */
	public abstract ProcessResultListVO<LogEduCourseStatusVO> listCourseStatusPageing(LogEduCourseStatusVO VO, int pageIndex, int listScale, int pageScale)  throws Exception;
	/**
	 * 팀별 과정 운영 현황 - 개설 과정 목록을 반환한다.(페이징)
	 * @param iQuizStareDTO
	 * @return
	 */
	public abstract ProcessResultListVO<LogEduCourseStatusVO> deptStatusList(LogEduCourseStatusVO vo) throws Exception;
	/**
	 * 교육 총괄 실적표용 과정 결과 목록 
	 * @param LogEduCourseStatusVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogEduCourseStatusVO> listCourseResult(
			LogEduCourseStatusVO vo) throws Exception;

	/**
	 * 교육 총괄 실적표용 과정 결과 목록 
	 * @param LogEduCourseStatusVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogEduCourseStatusVO> listCourseResultDash(
			LogEduCourseStatusVO vo) throws Exception;

	/**
	 * 상세정보 엑셀 다운로드
	 * @param LogEduCourseStatusVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract void listStdResultExcelDownload(LogEduCourseStatusVO vo, ArrayList<String> titleList, OutputStream os) throws Exception;
	
	/**
	 * 
	 * @param LogEduOrgStatusVO
	 * @return LogAdminConnLogVO
	 * @throws Exception
	 */
	public abstract LogEduOrgStatusVO viewResult(LogEduOrgStatusVO vo)
			throws Exception;

	/**
	 * 과정 운영 현황 다운로드
	* @param eduCourseStatusDTO
	* @param orgNm
	 * @param os
	 * @throws ServiceProcessException
	 */
	public abstract void listExcelDownload(LogEduCourseStatusVO vo, String orgNm, ArrayList<String> titleList, OutputStream os) throws Exception;

	/**
	 * 과정 총괄 현황 다운로드
	* @param eduCourseStatusDTO
	* @param orgNm
	 * @param os
	 * @throws ServiceProcessException
	 */
	public abstract void listExcelDownloadResult(LogEduCourseStatusVO vo, String orgNm, ArrayList<String> titleList, OutputStream os) throws Exception;

}