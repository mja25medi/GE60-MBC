package egovframework.edutrack.modules.course.attendance.service;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;

public interface AttendanceService {
	
	/**
	 * 회차 생성 시 출석 기간 등록
	 */
	public abstract void insertAttendDttm(AttendanceVO avo) throws Exception;

	/**
	 * 회차별 출석 조회
	 */
	public abstract ProcessResultListVO<AttendanceVO> listAttendance(AttendanceVO vo) throws Exception;
	
	/**
	 * 출석 기간 조회
	 */
	public abstract ProcessResultListVO<AttendanceVO> listPeriod(AttendanceVO vo) throws Exception;
	
	/**
	 *  출석기간 중 월요일을 조회
	 *	@param String
	 */
	public abstract ProcessResultListVO<AttendanceVO> listPeriodMonDay(AttendanceVO vo) throws Exception ;
	
	/**
	 *  월요일을 기준으로 해당주의 월~금을 조회
	 *	@param String
	 */
	public String periodDateStr(String attendDttm) throws Exception ;

	/**
	 * 날짜별 출석 조회
	 */
	public abstract ProcessResultVO<AttendanceVO> viewAttend(AttendanceVO vo) throws Exception;

	/**
	 * 출석표 수정 로그 조회
	 */
	public abstract ProcessResultListVO<AttendanceVO> selectUpdateLog(AttendanceVO vo) throws Exception;

	/**
	 * 유저 출석 상태 변경
	 */
	public abstract ProcessResultVO<AttendanceVO> updateAttendStat(AttendanceVO vo) throws Exception;

	/**
	 * 입실하기(출석)
	 */
	public abstract ProcessResultVO<AttendanceVO> enterClass(AttendanceVO vo) throws Exception;

	/**
	 * 퇴실하기
	 */
	public abstract ProcessResultVO<AttendanceVO> quitClass(AttendanceVO vo) throws Exception;

	/**
	 * 조퇴하기
	 * @param leftTime 
	 */
	public abstract ProcessResultVO<AttendanceVO> leftClass(AttendanceVO vo, int leftTime) throws Exception;

	/**
	 * 외출하기
	 * @param outTime, inTime 
	 */
	public abstract ProcessResultVO<AttendanceVO> outClass(AttendanceVO vo, int outTime, int inTime) throws Exception;

	/**
	 * 출석부 엑셀 다운로드
	 */
	public abstract void excelDownloadAttendList(AttendanceVO vo, CreateCourseVO ccvo,
			ServletOutputStream outputStream) throws Exception;
	
}