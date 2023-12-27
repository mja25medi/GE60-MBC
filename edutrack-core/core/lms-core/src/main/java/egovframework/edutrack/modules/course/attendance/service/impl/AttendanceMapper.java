package egovframework.edutrack.modules.course.attendance.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.attendance.service.AttendanceVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("attendanceMapper")
public interface AttendanceMapper {

	/**
	 *  출석기간 등록
	 *	@param AttendanceVO
	 */
	public void insertAttendDttm(AttendanceVO avo) throws DataAccessException ;

	/**
	 *  출석표 조회
	 *	@param AttendanceVO
	 */
	public List<AttendanceVO> listAttendance(AttendanceVO vo) throws DataAccessException ;
	
	/**
	 *  출석기간 조회
	 *	@param AttendanceVO
	 */
	public List<AttendanceVO> listPeriod(AttendanceVO vo) throws DataAccessException ;
	
	/**
	 *  출석기간 중 월요일을 조회
	 *	@param String
	 */
	public List<AttendanceVO> listPeriodMonDay(AttendanceVO vo) throws DataAccessException ;
	
	/**
	 *  월요일을 기준으로 해당주의 월~금을 조회
	 *	@param String
	 */
	public String periodDateStr(String attendDttm) throws DataAccessException ;

	/**
	 *  유저 상세 출석 조회
	 *	@param AttendanceVO
	 */
	public AttendanceVO viewAttend(AttendanceVO vo) throws DataAccessException ;

	/**
	 *  출석표 수정 로그 조회
	 *	@param AttendanceVO
	 */
	public List<AttendanceVO> selectUpdateLog(AttendanceVO vo) throws DataAccessException ;

	/**
	 *  출석 수정 로그 등록
	 *	@param AttendanceVO
	 */
	public int insertUpdateLog(AttendanceVO vo) throws DataAccessException ;

	/**
	 *  출석 상태 수정
	 *	@param AttendanceVO
	 */
	public int updateAttendStat(AttendanceVO vo) throws DataAccessException ;

	/**
	 * 입실하기(사용자 출석)
	 */
	public int enterClass(AttendanceVO vo) throws DataAccessException ;

	/**
	 * 퇴실하기(사용자)
	 */
	public int quitClass(AttendanceVO vo) throws DataAccessException ;

	/**
	 * 지각 처리(사용자)
	 */
	public int tardyCheck(AttendanceVO vo) throws DataAccessException ;

	/**
	 * 조퇴하기(사용자)
	 */
	public int leftClass(AttendanceVO vo) throws DataAccessException ;

	/**
	 * 외출하기(사용자)
	 */
	public int outClass(AttendanceVO vo) throws DataAccessException ;
}
